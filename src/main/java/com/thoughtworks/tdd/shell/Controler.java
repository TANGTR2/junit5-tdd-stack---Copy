package com.thoughtworks.tdd.shell;

import com.thoughtworks.tdd.core.*;

import static java.lang.Integer.parseInt;

public class Controler {
    private Request request;
    private ParkBoy parkBoy;
    private Response response;
    private  ParkinglotManager parkinglotManager;

    public Controler(Request request,ParkBoy parkBoy,Response response,ParkinglotManager parkinglotManager){
        this.request = request;
        this.parkBoy = parkBoy;
        this.response = response;
        this.parkinglotManager = parkinglotManager;
    }

    private String currentPage;

    public String parkPage() {
        if(parkBoy.getIsAllFullOfParkinglots()){
            response.send("车已停满，请晚点再来");
            this.getMainPage();
        }else {
            response.send("请输入车牌号:");
            currentPage = "park";
        }
        return currentPage;
    }

    public void park(){
        Car car = new Car();
        Receipt receipt = parkBoy.parking(car);
        car.setCarId(request.getCommand());
        response.send("停车成功，您的小票是：\n" + receipt.getReceiptId());
    }

    public void unpark() {
        Receipt receipt = new Receipt();
        receipt.setUuid(request.getCommand());
        Car car = parkBoy.unparking(receipt);
        String message;
        if (car == null) {
            message = "非法小票，无法取出车，请查证后再输";
        } else {
            message = "车已取出，您的车牌号是：" + car.getCarId();
        }
        response.send(message);
    }

    public void getMainPage(){
        response.send("1. 停车\n" + "2. 取车 \n" + "请输入您要进行的操作：");
    }

    public String wrongSelect(){
        response.send("非法指令，请查证后再输");
        this.getRootPage();
        currentPage = "root";
        return currentPage;
    }

    public String unparkPage() {
        response.send("请输入小票编号：");
        currentPage = "unpark";
        return currentPage;
    }

    public String handleParkPage() {
        this.park();
        this.getRootPage();
        currentPage = "root";
        return currentPage;
    }

    public String handleunparkPage() {
        this.unpark();
        this.getRootPage();
        currentPage = "root";
        return currentPage;
    }

    public void getRootPage() {
        response.send("1.停车服务\n" + "2.停车场管理\n" + "请输入您要进入的页面：");
    }

    public void showParkinglotPage() {
        response.send("1.查看停车场详情\n" + "2.添加停车场\n" + "3.删除停车场");
    }

    public String parkinglotPage() {
        response.send(parkinglotManager.getParkingLotsInfo());
        currentPage = "root";
        this.getRootPage();
        return currentPage;
    }

    public String addParkinglotPage() {
        response.send("请输入你要添加的停车场信息（格式为：名称，车位）：");
        currentPage = "addParkinglot";
        return  currentPage;
    }

    public String[] handleInformation(String command) {
        String[] div = command.split(",");
        return div;
    }

    public String addParkinglot() {
        String[] div = handleInformation(request.getCommand());
        parkinglotManager.addParkingLot(div[0],parseInt(div[1]));
        response.send("停车场添加成功！");
        this.getRootPage();
        currentPage = "root";
        return currentPage;
    }

    public String removeParkinglotPage() {
        response.send("请输入需要删除的被管理停车场ID:");
        currentPage = "removeParkinglot";
        return  currentPage;
    }

    public String removeParkinglot(){
        getDeleteParkingLot(parkBoy,request.getCommand());
        this.getRootPage();
        currentPage = "root";
        return currentPage;
    }

    public void getDeleteParkingLot(ParkBoy parkBoy, String command) {
        boolean isExist=false;
        for (ParkingLot parkingLot:parkBoy.getParkingLots()){
            if(parkingLot.getParklotId().equals(command) && parkingLot.getParkedCars().size()==0){
                isExist = true;
                parkBoy.removeParkingLot(parkingLot);
                response.send("停车场删除成功！");
                break;
            }else if (parkingLot.getParklotId().equals(command)){
                isExist = true;
                response.send("停车场删除失败，原因：此停车场中，依然停有汽车，无法删除！");
                break;
            }
        }
        if(!isExist){
            response.send("停车场删除失败，原因：此停车场不存在！");
        }
    }
}
