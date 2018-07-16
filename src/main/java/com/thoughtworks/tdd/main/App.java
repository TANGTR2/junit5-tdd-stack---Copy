package com.thoughtworks.tdd.main;

import com.thoughtworks.tdd.core.ParkBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.shell.Controler;
import com.thoughtworks.tdd.shell.Request;
import com.thoughtworks.tdd.shell.Response;
import com.thoughtworks.tdd.shell.Router;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot1);parkingLots.add(parkingLot2);
        ParkBoy parkBoy = new ParkBoy(parkingLots);
        Request request = new Request();
        Response response = new Response();
        Controler controler = new Controler(request,parkBoy,response);
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
