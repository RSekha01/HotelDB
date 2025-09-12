package com.example.hotelmanagement.repository;

import com.example.hotelmanagement.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
