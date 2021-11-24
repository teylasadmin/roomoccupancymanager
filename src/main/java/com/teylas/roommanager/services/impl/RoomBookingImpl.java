package com.teylas.roommanager.services.impl;

import com.teylas.roommanager.RoomType;
import com.teylas.roommanager.services.RoomBooking;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomBookingImpl implements RoomBooking {
    private List<Double> premiumCustomerMoney;
    private List<Double> economyCustomerMoney;
    final private List<Double> fromFile = Arrays.asList(23.0, 45.0, 155.0, 374.0, 22.0, 99.99, 100.0, 101.0, 115.0, 209.0);


    public RoomBookingImpl(){
        premiumCustomerMoney = fromFile.stream().filter(i->i>=100).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        economyCustomerMoney = fromFile.stream().filter(i->i<100).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    @Override
    public double getProfitPerRoomType(RoomType roomType, int numberOfRooms) {
        switch(roomType){
            case ECONOMY: return economyCustomerMoney.stream().limit(numberOfRooms).reduce(0.0, Double::sum);
            case PREMIUM:return premiumCustomerMoney.stream().limit(numberOfRooms).reduce(0.0, Double::sum);
            default: throw new RuntimeException("Unsupported room type");
        }
    }
}
