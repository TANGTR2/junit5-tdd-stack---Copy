package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int size;
    public Map<Receipt,Car> parkedCars = new HashMap<>();

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public void setParkedCars(Map<Receipt, Car> parkedCars) {
        this.parkedCars = parkedCars;
    }
    public Map<Receipt, Car> getParkedCars() {
        return parkedCars;
    }


    public ParkingLot(int size) {
        setSize(size);
    }

    public Receipt park(Car car) {
       if (size==0){
           throw new ParkingLotFullException("should park successfully");
       }
       this.size--;
       Receipt receipt = new Receipt();
       this.parkedCars.put(receipt,car);
       return receipt;
    }

    public Car unPark(Receipt receipt) {
        Car car = new Car();
        if(parkedCars.containsKey(receipt)){
            this.size++;
            car = this.parkedCars.get(receipt);
        }
        return car;
    }

    public boolean isFull() {
        if(this.size == 0)
            return true;
        else
            return false;
    }

}
