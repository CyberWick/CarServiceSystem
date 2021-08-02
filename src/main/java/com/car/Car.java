package com.car;

import com.customer.Customer;

import javax.persistence.*;

@Entity
@Table(name = "Car")
public class Car {
    @Id
    private String carNumber;

    @Column(name = "car_type", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "fuel_capacity", nullable = false)
    int fuelCapacity;

    @Column(name = "fuel_remaining", nullable = false)
    int fuelRemaining;

    public int getFuelRemaining() {
        return fuelRemaining;
    }

    public void setFuelRemaining(int fuelRemaining) {
        this.fuelRemaining = fuelRemaining;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(int fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
