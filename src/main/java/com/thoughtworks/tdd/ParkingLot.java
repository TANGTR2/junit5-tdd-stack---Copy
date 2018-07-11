package com.thoughtworks.tdd;

public class ParkingLot {
    private int size;
    public Car car;
    public Receipt receipt;

    public ParkingLot(int size) {
        this.size = size;
    }

    public Receipt park(Car car) {
       if (size==0){
           throw new ParkingLotFullException("should park successfully");
       }

       return receipt;
    }

    public Car unPark(Receipt receipt) {
        return car;

    }
}
