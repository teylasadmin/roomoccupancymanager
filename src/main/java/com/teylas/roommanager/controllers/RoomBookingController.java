package com.teylas.roommanager.controllers;

import com.teylas.roommanager.services.impl.RoomBookingImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/room-manager")
public class RoomBookingController {

    @Autowired
    RoomBookingImpl roomBooking;

    @PostMapping(path = "/profit-per-type")
    public ResponseEntity<String> roomProfitability(@RequestBody String numberOfRoomsPerType) {
        if(StringUtils.isBlank(numberOfRoomsPerType))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        final String[] roomsPerTypeArray = numberOfRoomsPerType.split(System.lineSeparator());

        int freePremiumRoom = 0;
        int freeEconomyRoom = 0 ;

        Pattern premiumRegexNumber = Pattern.compile("Free Premium rooms: ([\\d]+)");
        Matcher premiumMatcher = premiumRegexNumber.matcher(roomsPerTypeArray[0]);
        if (premiumMatcher.find()) {
            freePremiumRoom = Integer.valueOf(premiumMatcher.group(1));
        }

        Pattern economyRegexNumber = Pattern.compile("Free Economy rooms: ([\\d]+)");
        Matcher economyMatcher = economyRegexNumber.matcher(roomsPerTypeArray[1]);
        if (economyMatcher.find()) {
            freeEconomyRoom = Integer.valueOf(economyMatcher.group(1));
        }
        return new ResponseEntity<String>(roomBooking.getProfitPerRoomType(freeEconomyRoom,freePremiumRoom), HttpStatus.OK);
    }
}
