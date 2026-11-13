package com.lld.p04_creational.p01_factory.good_design.concrete;

import com.lld.p04_creational.p01_factory.good_design.Vehicle;

// Car.java - Concrete class for Car
public class Car implements Vehicle {
    public void start() {
        System.out.println("Car is starting...");
    }
    public void stop() {
        System.out.println("Car is stopping...");
    }
}
