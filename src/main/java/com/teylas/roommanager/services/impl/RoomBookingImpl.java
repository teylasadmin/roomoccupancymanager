package com.teylas.roommanager.services.impl;

import com.teylas.roommanager.RoomType;
import com.teylas.roommanager.services.RoomBooking;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static com.teylas.roommanager.Utils.formatDecimal;

@Service
public class RoomBookingImpl implements RoomBooking {

    private List<Double> premiumCustomerMoney;
    private List<Double> economyCustomerMoney;
    private List<Double> fromFile;


    public RoomBookingImpl(){
        Path path = Paths.get("src/main/resources/potential-guests.json");

        try {
            String data = Files.readAllLines(path).get(0);
            final Double[] potentialCustomersArray = Arrays.stream(data.substring(1, data.length() - 1).split(","))// strip square brackets
                    .map(Double::valueOf)
                    .toArray(Double[]::new);
            fromFile = Arrays.asList(potentialCustomersArray);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read and convert to List a file with data");
        }

        premiumCustomerMoney = fromFile.stream().filter(i -> i>=100).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        economyCustomerMoney = fromFile.stream().filter(i -> i<100).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
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
                int eligibleForUpgrade = numberOfRooms - occupiedRooms;
                if(eligibleForUpgrade >0) {
                    profit = economyCustomerMoney.stream().limit(eligibleForUpgrade).reduce(profit, Double::sum);
                }

                break;
            }
            default: throw new RuntimeException("Unsupported room type");
        }
        outputStatement = String.format("Usage %s: %s (EUR %s)",roomType.getType(),occupiedRooms, formatDecimal(profit));
        return outputStatement;
    }
}
