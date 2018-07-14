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
}
