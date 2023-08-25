package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final List<Car> carList = new ArrayList<>();

    public CarServiceImpl() {
        carList.add(new Car(2010, "Lada", "Green"));
        carList.add(new Car(1990, "Oka", "Rad"));
        carList.add(new Car(1970, "BMW", "Black"));
        carList.add(new Car(2023, "Muscovite", "Braun"));
        carList.add(new Car(2023, "Chevrolet Camaro", "Blue"));
    }

    @Override
    public List<Car> getCar(Integer count) {
        if (count >= 5) {
            return carList;
        }
        return carList.subList(0, count);
    }
}








