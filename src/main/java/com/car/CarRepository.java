package com.car;

import com.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CarRepository {

    /********NEEDS TO BE THROWN OUT************/
    public String saveCar(String carNumber, String type, String customerID, int fuelCapacity, int fuelRemaining) {
        Car car = new Car();
        car.setCarNumber(carNumber);
        car.setType(type);
        car.setFuelCapacity(fuelCapacity);
        car.setFuelRemaining(fuelRemaining);
//        car.setCustomerID(customerID);

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String id = (String) session.save(car);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public void updateCar(Car newCar) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(newCar);
        session.getTransaction().commit();
        session.close();
    }

    public List<Car> getAllCars() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Car> cars = (List<Car>) session.createQuery("from Car").list();
        session.getTransaction().commit();
        session.close();
        return cars;
    }

}
