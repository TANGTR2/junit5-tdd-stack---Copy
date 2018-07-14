package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class ParkingLotTest {
    @Test
    public void should_park_successfully_given_parking_lot_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        try {
            parkingLot.park(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }

    @Test
    public void should_park_failed_given_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(0);
        try {
            parkingLot.park(new Car());
            fail("should park successfully");
        } catch (ParkingLotFullException exception) {

        }
    }

    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right() {
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);

        assertThat(parkingLot.unPark(receipt), is(theCar));

    }

    @Test
    public void should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong() {
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);

        Receipt anotherReceipt = new Receipt();

        assertThat(parkingLot.unPark(anotherReceipt), not(theCar));
    }

    @Test
    public void should_be_true_when_call_isFull_given_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(0);

        assertThat(parkingLot.isFull(), is(true));
    }

    @Test
    public void should_be_false_when_call_isFull_given_parking_lot_is_not_full() {
        ParkingLot parkingLot = new ParkingLot(1);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_car() {
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_park_successfullly_when_call_park_again_given_a_full_parking_lot_take_out_a_car() {
        ParkingLot parkingLot = new ParkingLot(1);

        Car theCar = new Car();
        Receipt receipt = parkingLot.park(theCar);
        parkingLot.unPark(receipt);

        try {
            parkingLot.park(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }

    @Test
    public void should_parking_one_car_when_call_ParkBoy_parking_given_a_notfull_parking_lot() {
        //given
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        when(parkingLot1.isFull()).thenReturn(false);
        //when
        ParkBoy parkBoy = new ParkBoy(parkingLots);
        Car car = new Car();
        parkBoy.parking(car);
        //then
        verify(parkingLot1).park(car);
    }

    @Test
    public void should_unparking_one_car_when_call_ParkBoy_unparking_given_a_full_parking_lot() {
        //given
        ParkingLot parkingLot = new ParkingLot(1);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkBoy parkBoy = new ParkBoy(parkingLots);
        Car car = new Car();
        Receipt receipt = parkBoy.parking(car);
        //when
        parkBoy.unparking(receipt);
        //then
        assertThat(parkBoy.unparking(receipt), is(parkingLot.unPark(receipt)));
    }


    @Test
    public void should_parking_two_car_to_two_parkingLot_when_call_ParkBoy_parking_given_two_notfull_parking_lot() {
        //given
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        when(parkingLot1.isFull()).thenReturn(false);
        when(parkingLot2.isFull()).thenReturn(false);
        //when
        ParkBoy parkBoy = new ParkBoy(parkingLots);
        Car car1 = new Car();Car car2 = new Car();
        parkBoy.parking(car1);parkBoy.parking(car2);
        //then
        verify(parkingLot1).park(car1);
        verify(parkingLot1).park(car2);
}

    @Test
    public void should_unparking_two_car_from_two_parkingLot_when_call_ParkBoy_unparking_given_two_full_parking_lot() {
        //given
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car1 = new Car();Car car2 = new Car();
        ParkBoy parkBoy = new ParkBoy(parkingLots);
        Receipt receipt1 = parkBoy.parking(car1);
        Receipt receipt2 = parkBoy.parking(car2);
        //when //then
        assertThat(parkBoy.unparking(receipt1), is(parkingLot1.unPark(receipt1)));
        assertThat(parkBoy.unparking(receipt2), is(parkingLot2.unPark(receipt2)));
    }

    @Test
    public void should_parking_car_by_order_when_call_ParkBoy_parking() {
        //given
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        Car car1 = new Car();Car car2 = new Car();
        ParkBoy parkBoy = new ParkBoy(parkingLots);
        Receipt receipt1 = parkBoy.parking(car1);
        Receipt receipt2 = parkBoy.parking(car2);
        //when //then
        assertThat(parkBoy.unparking(receipt1), is(parkingLot2.unPark(receipt1)));
        assertThat(parkBoy.unparking(receipt2), is(parkingLot2.unPark(receipt2)));
    }

    @Test
    public void should_not_unparking_car_by_wrong_recript_when_call_ParkBoy_unparking() {
        //given
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        when(parkingLot1.isFull()).thenReturn(false);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        Car car1 = new Car();
        ParkBoy parkBoy = new ParkBoy(parkingLots);
        Receipt receipt1 = parkBoy.parking(car1);
        Receipt wrongReceipt = new Receipt();
        assertThat(parkBoy.unparking(wrongReceipt), not(car1));
    }

    @Test
    public void should_be_true_when_call_isAllFull_given_parkinglots_is_full() {
        //given
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        when(parkingLot1.isFull()).thenReturn(true);
        when(parkingLot2.isFull()).thenReturn(true);
        //when
        ParkBoy parkBoy = new ParkBoy(parkingLots);
        //then
        assertThat(parkBoy.getIsAllFullOfParkinglots(), is(true));
    }

    @Test
    public void should_be_false_when_call_isAllFull_given_have_notfull_parkinglot() {
        //given
        ParkingLot parkingLot1 = mock(ParkingLot.class);
        ParkingLot parkingLot2 = mock(ParkingLot.class);
        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);
        when(parkingLot1.isFull()).thenReturn(true);
        when(parkingLot2.isFull()).thenReturn(false);
        //when
        ParkBoy parkBoy = new ParkBoy(parkingLots);
        //then
        assertThat(parkBoy.getIsAllFullOfParkinglots(), is(false));
    }
}
