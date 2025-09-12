package com.example.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
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

    @Column(unique = true)
    private String roomNumber;

    // e.g. SINGLE, DOUBLE, SUITE
    private String type;

    private BigDecimal price;

    // AVAILABLE or BOOKED
    private String status = "AVAILABLE";

    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<Booking> bookings;
}
