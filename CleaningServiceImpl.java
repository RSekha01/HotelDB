package com.example.hotelmanagement.service.impl;

import com.example.hotelmanagement.model.Booking;
import com.example.hotelmanagement.model.CleaningRequest;
import com.example.hotelmanagement.repository.BookingRepository;
import com.example.hotelmanagement.repository.CleaningRepository;
import com.example.hotelmanagement.service.CleaningService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CleaningServiceImpl implements CleaningService {

    private final CleaningRepository cleaningRepository;
    private final BookingRepository bookingRepository;

    public CleaningServiceImpl(CleaningRepository cleaningRepository, BookingRepository bookingRepository) {
        this.cleaningRepository = cleaningRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public CleaningRequest requestCleaning(Long bookingId, String notes) {
        Booking b = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
        CleaningRequest req = new CleaningRequest();
        req.setBooking(b);
        req.setNotes(notes);
        req.setStatus("PENDING");
        return cleaningRepository.save(req);
    }

    @Override
    public List<CleaningRequest> getAllCleaningRequests() {
        return cleaningRepository.findAll();
    }

    @Override
    public CleaningRequest updateStatus(Long id, String status) {
        CleaningRequest r = cleaningRepository.findById(id).orElseThrow(() -> new RuntimeException("Cleaning request not found"));
        r.setStatus(status);
        return cleaningRepository.save(r);
    }
}
