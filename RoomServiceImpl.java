package com.example.hotelmanagement.service.impl;

import com.example.hotelmanagement.model.Room;
import com.example.hotelmanagement.repository.RoomRepository;
import com.example.hotelmanagement.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, Room room) {
        Room existing = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        existing.setRoomNumber(room.getRoomNumber());
        existing.setPrice(room.getPrice());
        existing.setType(room.getType());
        existing.setStatus(room.getStatus());
        return roomRepository.save(existing);
    }

    @Override
    public Optional<Room> getRoom(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
