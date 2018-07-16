package com.thoughtworks.tdd.main;

import com.thoughtworks.tdd.core.ParkBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.core.ParkinglotManager;
import com.thoughtworks.tdd.shell.Controler;
import com.thoughtworks.tdd.shell.Request;
import com.thoughtworks.tdd.shell.Response;
import com.thoughtworks.tdd.shell.Router;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(20);
        parkingLot1.setParklotId("001");
        parkingLot1.setParkinglotName("南方停车场");
        parkingLot1.setHadParkCarNum(5);
        ParkingLot parkingLot2 = new ParkingLot(10);
        parkingLot2.setParklotId("002");
        parkingLot2.setParkinglotName("北方停车场");
        parkingLot2.setHadParkCarNum(2);
        parkingLots.add(parkingLot1);parkingLots.add(parkingLot2);
        ParkBoy parkBoy = new ParkBoy(parkingLots);
        ParkinglotManager parkinglotManager = new ParkinglotManager(parkBoy,parkingLots);
        Request request = new Request();
        Response response = new Response();
        Controler controler = new Controler(request,parkBoy,response,parkinglotManager);
        //controler.getMainPage();
        controler.getRootPage();
        //Router router = new Router(controler,"main");
        Router router = new Router(controler,"root");
        Scanner in = new Scanner(System.in);

        try {
            while(true){
                String command = in.nextLine();
                request.setCommand(command);
                router.rocessRequest(request);
            }
        } catch (Exception e){
            System.err.println("App end!");
        }
    }
}
