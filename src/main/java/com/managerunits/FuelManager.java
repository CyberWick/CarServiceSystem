package com.managerunits;

public class FuelManager {
    public static int fuelInTank = 150;         //Set as static so that can be used multiple times during runtime( For some reason it doesn't seem to work) Will clarify it later!
    public static int fuelFilling(int fuelToBeFilled) {
        if(fuelToBeFilled == 0) {
            System.out.println("Car already had a full engine!");
            return 0;
        } else if (FuelManager.fuelInTank == 0) {
            System.out.println("No fuel left in tank and request to fill the tank sent!");
            return 0;
        } else {
            if(fuelToBeFilled > FuelManager.fuelInTank) {
//                int possibleFuelToFill = fuelInTank;
                System.out.println("Filled "+ FuelManager.fuelInTank+" ltrs of fuel. Now tank is empty and request to fill sent!");
                return FuelManager.fuelInTank;
            }
            System.out.println("Car has been filled "+ fuelToBeFilled+" lts of fuel and car tank is full!" );
            return fuelToBeFilled;
        }
    }
}
