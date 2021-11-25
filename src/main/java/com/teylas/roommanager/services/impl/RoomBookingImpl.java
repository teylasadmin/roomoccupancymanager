package com.teylas.roommanager.services.impl;

import com.teylas.roommanager.RoomType;
import com.teylas.roommanager.services.RoomBooking;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.teylas.roommanager.Utils.formatDecimal;

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
    public String getProfitPerRoomType(RoomType roomType, int numberOfRooms) {
        String outputStatement = "";
        Double profit = 0.0;
        int occupiedRooms = 0;
        switch(roomType){
            case ECONOMY: {
                profit = economyCustomerMoney.stream().limit(numberOfRooms).reduce(0.0, Double::sum);
                occupiedRooms = numberOfRooms > economyCustomerMoney.size()? economyCustomerMoney.size():numberOfRooms;
                break;
            }
            case PREMIUM: {
                profit = premiumCustomerMoney.stream().limit(numberOfRooms).reduce(0.0, Double::sum);
                occupiedRooms = numberOfRooms > premiumCustomerMoney.size()? premiumCustomerMoney.size():numberOfRooms;
                break;
            }
            default: throw new RuntimeException("Unsupported room type");
        }
        outputStatement = String.format("Usage %s: %s (EUR %s)",roomType.getType(),occupiedRooms, formatDecimal(profit));
        return outputStatement;
    }
}
