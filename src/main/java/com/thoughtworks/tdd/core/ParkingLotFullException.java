package com.thoughtworks.tdd.core;

public class ParkingLotFullException extends RuntimeException {
    public ParkingLotFullException(String message) {
        super(message);
    }
}
