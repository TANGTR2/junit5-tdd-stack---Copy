package com.thoughtworks.tdd;


public class PrintContent {
    public void printMainInterface() {
        System.out.println("" +
                "1. 停车\n" +
                "2. 取车 \n" +
                "请输入您要进行的操作：");
    }

    public void isTrueOfSelect(int select){
        if(select != 1 && select != 2)
            System.out.println("非法指令，请查证后再输");
    }

    public void printParkingContent(ParkBoy parkBoy){
        if(parkBoy.getIsAllFullOfParkinglots()){
            System.out.println("车已停满，请晚点再来");
        }
        else
            System.out.println("请输入车牌号:");
    }

}
