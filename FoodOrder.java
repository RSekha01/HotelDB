package com.example.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "FOOD_ORDERS")
@Data
@NoArgsConstructor
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_order_seq")
    @SequenceGenerator(name="food_order_seq", sequenceName="FOOD_ORDER_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // optional: link to booking if order is for a booked room
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable=true)
    private Booking booking;

    @OneToMany(mappedBy = "foodOrder", cascade = CascadeType.ALL)
    private List<FoodOrderItem> items;

    private BigDecimal totalPrice;

    // PLACED, PREPARING, DELIVERED, CANCELLED
    private String status = "PLACED";

    private LocalDateTime createdAt = LocalDateTime.now();
}
