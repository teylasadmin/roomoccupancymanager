package com.teylas.roommanager.services.impl;

import com.teylas.roommanager.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class RoomBookingImplTest {

    @Autowired
    RoomBookingImpl roomBooking;

    @Test
    void test1() {
        final String profitPerRoomTypePremium = roomBooking.getProfitPerRoomType(RoomType.PREMIUM, 3);
        assertEquals("Usage Premium: 3 (EUR 738)",profitPerRoomTypePremium);
        final String profitPerRoomTypeEconomy = roomBooking.getProfitPerRoomType(RoomType.ECONOMY, 3);
        assertEquals("Usage Economy: 3 (EUR 167.99)",profitPerRoomTypeEconomy);
    }

    @Test
    void test2() {
        final String profitPerRoomTypePremium = roomBooking.getProfitPerRoomType(RoomType.PREMIUM, 7);
        assertEquals("Usage Premium: 6 (EUR 1054)",profitPerRoomTypePremium);
        final String profitPerRoomTypeEconomy = roomBooking.getProfitPerRoomType(RoomType.ECONOMY, 5);
        assertEquals("Usage Economy: 4 (EUR 189.99)",profitPerRoomTypeEconomy);
    }

    @Test
    void test3() {
        final String profitPerRoomTypePremium = roomBooking.getProfitPerRoomType(RoomType.PREMIUM, 2);
        assertEquals("Usage Premium: 2 (EUR 583)",profitPerRoomTypePremium);
        final String profitPerRoomTypeEconomy = roomBooking.getProfitPerRoomType(RoomType.ECONOMY, 7);
        assertEquals("Usage Economy: 4 (EUR 189.99)",profitPerRoomTypeEconomy);
    }

    @Test
    void test4() {
        final String profitPerRoomTypePremium = roomBooking.getProfitPerRoomType(RoomType.PREMIUM, 7);
        assertEquals("Usage Premium: 7 (EUR 1153.99)",profitPerRoomTypePremium);
        final String profitPerRoomTypeEconomy = roomBooking.getProfitPerRoomType(RoomType.ECONOMY, 1);
        assertEquals("Usage Economy: 1 (EUR 45)",profitPerRoomTypeEconomy);
    }
}