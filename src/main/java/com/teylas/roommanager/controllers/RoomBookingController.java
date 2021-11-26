package com.teylas.roommanager.controllers;

import com.teylas.roommanager.services.impl.RoomBookingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/room-manager")
public class RoomBookingController {

    @Autowired
    RoomBookingImpl roomBooking;

    @PostMapping(path = "/profit-per-type", consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String roomProfitability(@RequestBody String numberOfRoomsPerType) {
        return roomBooking.getProfitPerRoomType(3,3);
    }
}
