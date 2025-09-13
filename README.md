package com.example.hotelmanagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;
    private String type;
    private Double price;
    private String status; // AVAILABLE / BOOKED

    // List of image URLs or paths
    @ElementCollection
    private List<String> imageUrls;

    // Getters and Setters
    // ...
}
