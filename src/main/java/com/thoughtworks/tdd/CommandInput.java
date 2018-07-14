package com.thoughtworks.tdd;

import java.util.Scanner;

public class CommandInput {
    public static void main(String[] args) {
        while(true) {
            PrintContent printContent = new PrintContent();
            printContent.printMainInterface();
            Scanner input1 = new Scanner(System.in);
            int select = input1.nextInt();
            printContent.isTrueOfSelect(select);
        }
    }
}
