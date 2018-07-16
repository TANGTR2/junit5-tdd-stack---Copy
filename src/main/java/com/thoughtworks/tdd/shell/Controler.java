package com.thoughtworks.tdd.shell;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkBoy;
import com.thoughtworks.tdd.core.Receipt;

public class Controler {
    private Request request;
    private ParkBoy parkBoy;
    private Response response;

    public Controler(Request request,ParkBoy parkBoy,Response response){
        this.request = request;
        this.parkBoy = parkBoy;
        this.response = response;
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
        this.getMainPage();
        currentPage = "main";
        return currentPage;
    }

    public String handleunparkPage() {
        this.unpark();
        this.getMainPage();
        currentPage = "main";
        return currentPage;
    }

    public void getRootPage() {
        response.send("1.停车服务\n" + "2.停车场管理\n" + "请输入您要进入的页面：");
    }
}
