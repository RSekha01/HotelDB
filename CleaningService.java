package com.example.hotelmanagement.service;

import com.example.hotelmanagement.model.CleaningRequest;

import java.util.List;

public interface CleaningService {
    CleaningRequest requestCleaning(Long bookingId, String notes);
    List<CleaningRequest> getAllCleaningRequests();
    CleaningRequest updateStatus(Long id, String status);
}
