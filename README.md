package com.example.hotelmanagement.service;

import com.example.hotelmanagement.model.FoodOrder;
import com.example.hotelmanagement.model.MenuItem;

import java.util.List;
import java.util.Map;

public interface FoodService {
    List<MenuItem> getMenu();
    FoodOrder placeOrder(Long userId, Long bookingId, Map<Long,Integer> itemIdToQty);
}
