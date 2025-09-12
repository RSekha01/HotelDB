package com.example.hotelmanagement.service;

import com.example.hotelmanagement.model.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {
    Room createRoom(Room room);
    Room updateRoom(Long id, Room room);
    Optional<Room> getRoom(Long id);
    List<Room> getAllRooms();
}
