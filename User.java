package com.example.hotelmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name="user_seq", sequenceName="USER_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password; // plaintext for demo only

    // role: "ADMIN" or "USER"
    private String role = "USER";

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Booking> bookings;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<FoodOrder> foodOrders;
}
