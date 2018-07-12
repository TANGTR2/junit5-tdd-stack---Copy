package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int size;
    private Map<Receipt,Car> parkedCars = new HashMap<>();

    public ParkingLot(int size) {
        this.size = size;
    }

    public Receipt park(Car car) {
       if (size==0){
           throw new ParkingLotFullException("should park successfully");
       }
       this.size++;
       Receipt receipt = new Receipt();
       this.parkedCars.put(receipt,car);
       return receipt;
    }

    public Car unPark(Receipt receipt) {
        this.size--;
        return this.parkedCars.get(receipt);

    }

    public boolean isFull() {
        if(this.size == 0)
            return true;
        else
            return false;
    }


}
