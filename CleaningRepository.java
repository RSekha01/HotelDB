package com.example.hotelmanagement.repository;

import com.example.hotelmanagement.model.CleaningRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CleaningRepository extends JpaRepository<CleaningRequest, Long> {
}
