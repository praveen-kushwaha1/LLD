package com.lld.p04_creational.p01_factory.bad_design.concrete;


import com.lld.p04_creational.p01_factory.bad_design.Vehicle;

// Bike.java - Concrete class for Bike
public class Bike implements Vehicle {
    public void start() {
        System.out.println("Bike is starting...");
    }
    public void stop() {
        System.out.println("Bike is stopping...");
    }
}