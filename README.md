package com.example.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROOMS")
@Data
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_seq")
    @SequenceGenerator(name="room_seq", sequenceName="ROOM_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomNumber;

    // e.g. SINGLE, DOUBLE, SUITE
    private String type;

    private BigDecimal price;

    // AVAILABLE or BOOKED
    private String status = "AVAILABLE";

    // Relationship with Booking
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Booking> bookings = new ArrayList<>();

    // List of image URLs or paths for room photos
    @ElementCollection
    private List<String> imageUrls = new ArrayList<>();
}
