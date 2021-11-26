package com.teylas.roommanager.services.impl;

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
        final String profitPerRoomType = roomBooking.getProfitPerRoomType(3, 3);
        assertEquals("Usage Premium: 3 (EUR 738)\n" +
                "Usage Economy: 3 (EUR 167.99)",profitPerRoomType);
    }

    @Test
    void test2() {
        final String profitPerRoomType = roomBooking.getProfitPerRoomType(5, 7);
        assertEquals("Usage Premium: 6 (EUR 1054)\n" +
                "Usage Economy: 4 (EUR 189.99)",profitPerRoomType);
    }

    @Test
    void test3() {
        final String profitPerRoomType = roomBooking.getProfitPerRoomType(7, 2);
        assertEquals("Usage Premium: 2 (EUR 583)\n" +
                "Usage Economy: 4 (EUR 189.99)",profitPerRoomType);
    }

    @Test
    void test4() {
        final String profitPerRoomType = roomBooking.getProfitPerRoomType(1, 7);
        assertEquals("Usage Premium: 7 (EUR 1153.99)\n" +
                "Usage Economy: 1 (EUR 45)",profitPerRoomType);
    }
}