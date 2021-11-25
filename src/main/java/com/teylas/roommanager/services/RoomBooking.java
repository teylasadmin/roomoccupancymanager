package com.teylas.roommanager.services;

import com.teylas.roommanager.RoomType;

public interface RoomBooking {
    public String getProfitPerRoomType(RoomType roomType, int numberOfRooms);
}
