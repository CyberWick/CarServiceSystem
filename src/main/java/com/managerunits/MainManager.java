package com.managerunits;

import com.car.Car;

public class MainManager {
    public static Car startService(Car car) throws InterruptedException {
        System.out.println("Car has been serviced and gone for fueling");
        Thread.sleep(2000);
        int fuelFilled = FuelManager.fuelFilling(car.getFuelCapacity() - car.getFuelRemaining());
        car.setFuelRemaining(car.getFuelRemaining() + fuelFilled);
        System.out.println("Fuel has been taken care off and moving the car to washing center!");
        Thread.sleep(2000);
        int shampooRequired = WashingManager.startWashing(car);
        System.out.println("Washing service completed and now generating bill for the services!");
        Thread.sleep(2000);
        int cost = BillingManager.costForService(fuelFilled, shampooRequired);
        System.out.println("Car was service @"+ cost+". See you soon! Thank you");
        return car;
    }

}
