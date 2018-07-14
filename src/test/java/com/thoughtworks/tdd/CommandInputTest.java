package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommandInputTest {
    @Test
    public void should_print_correct_content_when_call_getIsAllFullOfParkinglots(){
        ParkBoy parkBoy = mock(ParkBoy.class);
        when(parkBoy.getIsAllFullOfParkinglots()).thenReturn(true);
        PrintContent printContent = new PrintContent();
        printContent.printParkingContent(parkBoy);
    }

    @Test
    public void should_print_correct_content1_when_call_getIsAllFullOfParkinglots(){
        ParkBoy parkBoy = mock(ParkBoy.class);
        when(parkBoy.getIsAllFullOfParkinglots()).thenReturn(false);
        PrintContent printContent = new PrintContent();
        printContent.printParkingContent(parkBoy);
    }
}
