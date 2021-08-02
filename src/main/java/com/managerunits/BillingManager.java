package com.managerunits;

import static com.car.GlobalVariablesCar.FUEL_PRICE;
import static com.car.GlobalVariablesCar.SHAMPOO_PRICE;

public class BillingManager {
    public static int costForService(int fuelRequired, int shampooRequired) { //Created a separate class to add complex logic for future
        int cost;
        cost = (fuelRequired*FUEL_PRICE) + (shampooRequired*SHAMPOO_PRICE);
        cost *= 1.18; //GST
        cost *=0.9; //Customer discount
        return cost;
    }
}
