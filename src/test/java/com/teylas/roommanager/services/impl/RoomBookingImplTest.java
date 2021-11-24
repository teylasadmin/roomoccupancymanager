package com.teylas.roommanager.services.impl;

import com.teylas.roommanager.RoomType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RoomBookingImplTest {

    @Autowired
    RoomBookingImpl roomBooking;

    @Test
    void getProfitPerRoomType() {
        final double profitPerRoomTypePremium = roomBooking.getProfitPerRoomType(RoomType.PREMIUM, 3);
        assertEquals(738,profitPerRoomTypePremium);
        final double profitPerRoomTypeEconomy = roomBooking.getProfitPerRoomType(RoomType.ECONOMY, 3);
        assertEquals(167.99,profitPerRoomTypeEconomy);
    }
}