package com.example.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CLEANING_REQUESTS")
@Data
@NoArgsConstructor
public class CleaningRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clean_seq")
    @SequenceGenerator(name="clean_seq", sequenceName="CLEAN_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private LocalDateTime requestedAt = LocalDateTime.now();

    // PENDING, IN_PROGRESS, COMPLETED
    private String status = "PENDING";

    private String notes;
}
