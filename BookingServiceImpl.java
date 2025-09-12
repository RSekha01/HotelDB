package com.example.hotelmanagement.service.impl;

import com.example.hotelmanagement.model.Booking;
import com.example.hotelmanagement.model.Room;
import com.example.hotelmanagement.model.User;
import com.example.hotelmanagement.repository.BookingRepository;
import com.example.hotelmanagement.repository.RoomRepository;
import com.example.hotelmanagement.repository.UserRepository;
import com.example.hotelmanagement.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public Booking bookRoom(Long userId, Long roomId, LocalDate checkIn, LocalDate checkOut) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room not found"));

        if (!"AVAILABLE".equalsIgnoreCase(room.getStatus())) {
            throw new RuntimeException("Room is not available");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckInDate(checkIn);
        booking.setCheckOutDate(checkOut);
        booking.setStatus("BOOKED");

        // mark room as booked
        room.setStatus("BOOKED");
        roomRepository.save(room);

        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public Booking cancelBooking(Long bookingId) {
        Booking b = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
        b.setStatus("CANCELLED");
        Room room = b.getRoom();
        if (room != null) {
            room.setStatus("AVAILABLE");
            roomRepository.save(room);
        }
        return bookingRepository.save(b);
    }

    @Override
    public List<Booking> getBookingsForUser(Long userId) {
        User u = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return bookingRepository.findByUser(u);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
