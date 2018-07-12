package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class ParkBoy {
    private ArrayList<ParkingLot> parkManage = new ArrayList<>();
    public ParkBoy(ArrayList<ParkingLot> parkManage) {

    }

    public boolean isHaveNotFullParkingLot(ArrayList<ParkingLot> parkManage) {
        for(ParkingLot parkingLot:parkManage){
            if(parkingLot.isFull())
                continue;
            else
                return true;
        }
        return false;
    }
}
