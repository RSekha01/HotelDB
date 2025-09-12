package com.example.hotelmanagement.service;

import com.example.hotelmanagement.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    Booking bookRoom(Long userId, Long roomId, LocalDate checkIn, LocalDate checkOut);
    Booking cancelBooking(Long bookingId);
    List<Booking> getBookingsForUser(Long userId);
    List<Booking> getAllBookings();
}
