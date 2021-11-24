package com.teylas.roommanager.services;

import com.teylas.roommanager.RoomType;

public interface RoomBooking {
    public double getProfitPerRoomType(RoomType roomType, int numberOfRooms);
}
