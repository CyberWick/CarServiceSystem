package com.managerunits;

import com.car.Car;

import static com.car.GlobalVariablesCar.*;

public class WashingManager {
    public static int shampooLeft = 50;
    public static final int shampooRequiredForCar[] = {30, 50, 80};

    public static int startWashing(Car car) {
        int shampooRequired = 0;

        if(car.getType().equals(HATCHBACK)) {  //This logic can be replaced with a map or a much simple switch
            shampooRequired = shampooRequiredForCar[0];
        } else if (car.getType().equals(SEDAN)) {
            shampooRequired = shampooRequiredForCar[1];
        } else if (car.getType().equals(SUV)) {
            shampooRequired = shampooRequiredForCar[2];
        }
        
        if(shampooRequired > shampooLeft) {
            System.out.println("Shampoo insufficient to wash the car!");
            shampooRequired = 0;
        }
        return shampooRequired;
    }
}
