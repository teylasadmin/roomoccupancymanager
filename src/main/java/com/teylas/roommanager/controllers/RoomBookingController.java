package com.teylas.roommanager.controllers;

import com.teylas.roommanager.services.impl.RoomBookingImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/room-manager")
public class RoomBookingController {

    @Autowired
    RoomBookingImpl roomBooking;

    @PostMapping(path = "/profit-per-type")
    public String roomProfitability(@RequestBody String numberOfRoomsPerType) {
        return null;
    }
}
