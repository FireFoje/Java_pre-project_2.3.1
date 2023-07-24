package web.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;


@Configuration
@PropertySource("classpath:db.properties")
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan(value = "web")
public class AppConfig {


    private final Environment env;

    public AppConfig(Environment env) {
        this.env = env;
    }


    @Bean
    public DataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("db.driver"));
        dataSource.setJdbcUrl(env.getProperty("db.url"));
        dataSource.setUser(env.getProperty("db.username"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean managerFactoryBean() throws PropertyVetoException {
        JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean containerEntityManager = new LocalContainerEntityManagerFactoryBean();
        containerEntityManager.setDataSource(getDataSource());
        containerEntityManager.setPackagesToScan("web.model");
        containerEntityManager.setJpaVendorAdapter(adapter);
        return containerEntityManager;
    }

    @Bean
    public PlatformTransactionManager getTransactionManager(EntityManagerFactory managerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(managerFactory);
        return transactionManager;
    }
}