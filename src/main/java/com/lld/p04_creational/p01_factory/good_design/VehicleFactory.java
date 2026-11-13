package com.lld.p04_creational.p01_factory.good_design;


import com.lld.p04_creational.p01_factory.good_design.concrete.Bike;
import com.lld.p04_creational.p01_factory.good_design.concrete.Car;
import com.lld.p04_creational.p01_factory.good_design.concrete.Truck;

public class VehicleFactory {
    public static Vehicle getVehicle(String vehicleType) {
        if (vehicleType.equals("Car")) {
            return new Car();
        } else if (vehicleType.equals("Truck")) {
            return new Truck();
        } else if (vehicleType.equals("Bike")) {
            return new Bike();
        } else {
            throw new IllegalArgumentException("Unknown vehicle type");
        }
    }
}