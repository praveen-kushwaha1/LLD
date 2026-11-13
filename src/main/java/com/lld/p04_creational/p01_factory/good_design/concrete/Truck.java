package com.lld.p04_creational.p01_factory.good_design.concrete;

import com.lld.p04_creational.p01_factory.good_design.Vehicle;

// Truck.java - Concrete class for Truck
public class Truck implements Vehicle {
    public void start() {
        System.out.println("Truck is starting...");
    }
    public void stop() {
        System.out.println("Truck is stopping...");
    }
}