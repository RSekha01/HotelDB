package com.example.hotelmanagement.service.impl;

import com.example.hotelmanagement.model.*;
import com.example.hotelmanagement.repository.*;
import com.example.hotelmanagement.service.FoodService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FoodServiceImpl implements FoodService {

    private final MenuItemRepository menuItemRepository;
    private final FoodOrderRepository foodOrderRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public FoodServiceImpl(MenuItemRepository menuItemRepository,
                           FoodOrderRepository foodOrderRepository,
                           UserRepository userRepository,
                           BookingRepository bookingRepository) {
        this.menuItemRepository = menuItemRepository;
        this.foodOrderRepository = foodOrderRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<MenuItem> getMenu() {
        return menuItemRepository.findAll();
    }

    @Override
    @Transactional
    public FoodOrder placeOrder(Long userId, Long bookingId, Map<Long, Integer> itemIdToQty) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Booking booking = null;
        if (bookingId != null) {
            booking = bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
        }

        FoodOrder order = new FoodOrder();
        order.setUser(user);
        order.setBooking(booking);

        List<FoodOrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> e : itemIdToQty.entrySet()) {
            Long menuId = e.getKey();
            Integer qty = e.getValue();
            MenuItem menuItem = menuItemRepository.findById(menuId).orElseThrow(() -> new RuntimeException("Menu item not found: " + menuId));
            FoodOrderItem oi = new FoodOrderItem();
            oi.setMenuItem(menuItem);
            oi.setQuantity(qty);
            oi.setFoodOrder(order);
            items.add(oi);

            BigDecimal line = menuItem.getPrice().multiply(BigDecimal.valueOf(qty));
            total = total.add(line);
        }

        order.setItems(items);
        order.setTotalPrice(total);
        order.setStatus("PLACED");

        return foodOrderRepository.save(order);
    }
}
