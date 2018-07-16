package com.thoughtworks.tdd.core;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private String parklotId;
    private String parkinglotName;
    private int size;
    private int hadParkCarNum;

    public int getHadParkCarNum() {
        return hadParkCarNum;
    }
    public void setHadParkCarNum(int hadParkCarNum) {
        this.hadParkCarNum = hadParkCarNum;
    }

    public String getParklotId() {
        return parklotId;
    }
    public void setParklotId(String parklotId) {
        this.parklotId = parklotId;
    }

    public String getParkinglotName() {
        return parkinglotName;
    }
    public void setParkinglotName(String parkinglotName) {
        this.parkinglotName = parkinglotName;
    }

    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

    public Map<Receipt,Car> parkedCars = new HashMap<>();

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
