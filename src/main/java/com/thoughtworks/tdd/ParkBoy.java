package com.thoughtworks.tdd;

import java.util.ArrayList;

public class ParkBoy {
    public ArrayList<ParkingLot> getParkManage() {
        return parkManage;
    }

    public void setParkManage(ArrayList<ParkingLot> parkManage) {
        this.parkManage = parkManage;
    }

    private ArrayList<ParkingLot> parkManage = new ArrayList<>();
    public ParkBoy(ArrayList<ParkingLot> parkManage) {
        this.parkManage = parkManage;

    }

    public Receipt parking(Car car) {
        Receipt receipt = new Receipt();
        parkManage = getParkManage();
        for(ParkingLot parkingLot : getParkManage()){
            if(!parkingLot.isFull()){
                receipt = parkingLot.park(car);
            }else
                continue;
        }
        return receipt;
    }

    public Car unparking(Receipt receipt) {
        Car car = new Car();
        for(ParkingLot parkingLot : getParkManage()){
            if(parkingLot.getParkedCars().containsKey(receipt))
                car = parkingLot.unPark(receipt);
        }
        return car;
    }
}
