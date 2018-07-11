package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int size;
    public Map<Receipt,Car> parkedCars = new HashMap<>();

    public ParkingLot(int size) {
        this.size = size;
    }

    public Receipt park(Car car) {
       if (size==0){
           throw new ParkingLotFullException("should park successfully");
       }
       Receipt receipt = new Receipt();
       this.parkedCars.put(receipt,car);
       return receipt;
    }

    public Car unPark(Receipt receipt) {
        this.size++;
        return this.parkedCars.get(receipt);

    }
}
