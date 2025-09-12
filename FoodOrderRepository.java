package com.example.hotelmanagement.repository;

import com.example.hotelmanagement.model.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
}
