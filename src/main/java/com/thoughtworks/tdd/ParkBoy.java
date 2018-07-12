package com.thoughtworks.tdd;

import java.util.ArrayList;

public class ParkBoy {
    private ArrayList<ParkingLot> parkManage = new ArrayList<>();
    public ParkBoy(ArrayList<ParkingLot> parkManage) {

    }

    public Receipt parking(ArrayList<ParkingLot> parkManage) {
        Receipt receipt = new Receipt();
        for(ParkingLot parkingLot : parkManage){
            if(!parkingLot.isFull()){
                receipt = parkingLot.park(new Car());
            }else
                continue;
        }
        return receipt;
    }
}
