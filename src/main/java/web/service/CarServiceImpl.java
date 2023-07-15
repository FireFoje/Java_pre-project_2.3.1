package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {
    private List<Car> carList = new ArrayList<>();

    {
        carList.add(new Car("Audi", "Q7", 2016));
        carList.add(new Car("BMW", "3", 2005));
        carList.add(new Car("Mercedes", "E-class", 2009));
        carList.add(new Car("WolksVagen", "Multivan", 2009));
        carList.add(new Car("Toyota", "Prius", 100000));
    }

    @Override
    public List<Car> carList(Integer count) {
        return carList.stream().limit(count).collect(Collectors.toList());
    }
}
