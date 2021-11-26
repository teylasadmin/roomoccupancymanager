package com.teylas.roommanager.services.impl;

import com.teylas.roommanager.RoomType;
import com.teylas.roommanager.services.RoomBooking;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    public String getProfitPerRoomType(int numberOfEconomyRooms, int numberOfPremiumRooms) {
        StringBuffer stringBuffer = new StringBuffer();

        Double profitEconomy = 0.0;
        Double profitPremium = 0.0;
        int occupiedEconomyRooms = 0;
        int occupiedPremiumRooms = 0;
        int numberOfUpgrades = 0;

        profitPremium = premiumCustomerMoney.stream().limit(numberOfPremiumRooms).reduce(0.0, Double::sum);
        occupiedPremiumRooms = numberOfPremiumRooms > premiumCustomerMoney.size()? premiumCustomerMoney.size():numberOfPremiumRooms;

        int guestsNeedingUpgrade = economyCustomerMoney.size() - numberOfEconomyRooms;
        int roomsAvailableForUpgrade = numberOfPremiumRooms - occupiedPremiumRooms;
        if(guestsNeedingUpgrade > 0 && roomsAvailableForUpgrade >0) {
            profitPremium = economyCustomerMoney.stream().limit(roomsAvailableForUpgrade).reduce(profitPremium, Double::sum);
            numberOfUpgrades = (guestsNeedingUpgrade>roomsAvailableForUpgrade)?roomsAvailableForUpgrade:guestsNeedingUpgrade;
            occupiedPremiumRooms+=numberOfUpgrades;
        }

        profitEconomy = economyCustomerMoney.subList(numberOfUpgrades ,economyCustomerMoney.size()).stream().limit(numberOfEconomyRooms).reduce(0.0, Double::sum);
        occupiedEconomyRooms = numberOfEconomyRooms > economyCustomerMoney.size()? economyCustomerMoney.size():numberOfEconomyRooms;

        stringBuffer.append(String.format("Usage %s: %s (EUR %s)",RoomType.PREMIUM.getType(),occupiedPremiumRooms, formatDecimal(profitPremium)));
        stringBuffer.append(System.lineSeparator());
        stringBuffer.append(String.format("Usage %s: %s (EUR %s)",RoomType.ECONOMY.getType(),occupiedEconomyRooms, formatDecimal(profitEconomy)));

        return stringBuffer.toString();
    }
}
