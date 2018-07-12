package com.thoughtworks.tdd;

import java.util.ArrayList;

public class ParkBoy {
    public ArrayList<ParkingLot> parkingLots ;
    public Receipt receipt;

    public ParkBoy(ArrayList<ParkingLot> parkManage) {
        setParkManage(parkManage);
    }

    public Receipt getReceipt() {
        return receipt;
    }
    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }


    public void setParkManage(ArrayList<ParkingLot> parkManage) {
        this.parkingLots = parkManage;
    }
    public ArrayList<ParkingLot> getParkManage() {
        return parkingLots;
    }

    public Receipt parking(Car car) {
        for(ParkingLot parkingLot : parkingLots){
            if(!parkingLot.isFull()){
                receipt = parkingLot.park(car);break;
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

}
