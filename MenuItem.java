package com.example.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "MENU_ITEMS")
@Data
@NoArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_seq")
    @SequenceGenerator(name="menu_seq", sequenceName="MENU_SEQ", allocationSize = 1)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Boolean available = true;
}
