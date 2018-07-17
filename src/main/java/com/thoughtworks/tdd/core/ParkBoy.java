package com.thoughtworks.tdd.core;

import java.util.ArrayList;
import java.util.List;

public class ParkBoy {

    public ArrayList<ParkingLot> parkingLots ;

    public Receipt receipt;

    public ParkBoy(ArrayList<ParkingLot> parkingLots) {
        setParkingLots(parkingLots);
    }

    public void removeParkingLot(ParkingLot parkingLot){
        parkingLots.remove(parkingLot);
    }

    public ArrayList<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(ArrayList<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Receipt parking(Car car) {
        for(ParkingLot parkingLot : parkingLots){
            if(!parkingLot.isFull()){
                receipt = parkingLot.park(car);
                break;
            }
        }
        return receipt;
    }

    public Car unparking(Receipt receipt) {
        Car car = new Car();
        for(ParkingLot parkingLot : parkingLots){
            if(parkingLot.getParkedCars().containsKey(receipt))
                car = parkingLot.unPark(receipt);
        }
        return car;
    }

    public boolean getIsAllFullOfParkinglots() {
        for(ParkingLot parkingLot : parkingLots){
            if(!parkingLot.isFull()){
                return false;
            }
        }
        return true;
    }
}
