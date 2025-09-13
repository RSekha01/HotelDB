package com.example.hotelmanagement.service;

import com.example.hotelmanagement.model.FoodOrder;
import com.example.hotelmanagement.model.FoodOrderItem;
import com.example.hotelmanagement.model.MenuItem;
import com.example.hotelmanagement.model.User;
import com.example.hotelmanagement.repository.FoodOrderRepository;
import com.example.hotelmanagement.repository.FoodOrderItemRepository;
import com.example.hotelmanagement.repository.MenuItemRepository;
import com.example.hotelmanagement.repository.UserRepository;
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
    private final FoodOrderItemRepository foodOrderItemRepository;
    private final UserRepository userRepository;

    public FoodServiceImpl(MenuItemRepository menuItemRepository,
                           FoodOrderRepository foodOrderRepository,
                           FoodOrderItemRepository foodOrderItemRepository,
                           UserRepository userRepository) {
        this.menuItemRepository = menuItemRepository;
        this.foodOrderRepository = foodOrderRepository;
        this.foodOrderItemRepository = foodOrderItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<MenuItem> getMenu() {
        return menuItemRepository.findAll();
    }

    @Override
    @Transactional
    public FoodOrder placeOrder(Long userId, Long bookingId, Map<Long, Integer> itemIdToQty) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found: " + userId));

        FoodOrder order = new FoodOrder();
        order.setUser(user);
        order.setStatus("PLACED");
        order.setItems(new ArrayList<>());

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Map.Entry<Long, Integer> entry : itemIdToQty.entrySet()) {
            Long menuItemId = entry.getKey();
            Integer quantity = entry.getValue();

            MenuItem menuItem = menuItemRepository.findById(menuItemId)
                    .orElseThrow(() -> new RuntimeException("Menu item not found: " + menuItemId));

            // ✅ Check stock (using available column)
            if (menuItem.getAvailable() < quantity) {
                throw new RuntimeException("Not enough stock for item: " + menuItem.getName());
            }

            // Reduce stock
            menuItem.setAvailable(menuItem.getAvailable() - quantity);
            menuItemRepository.save(menuItem);

            // Create order item
            FoodOrderItem orderItem = new FoodOrderItem();
            orderItem.setFoodOrder(order);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(quantity);
            order.getItems().add(orderItem);

            // Update total price
            totalPrice = totalPrice.add(menuItem.getPrice().multiply(BigDecimal.valueOf(quantity)));
        }

        order.setTotalPrice(totalPrice);

        // Save order and items
        FoodOrder savedOrder = foodOrderRepository.save(order);
        for (FoodOrderItem item : savedOrder.getItems()) {
            foodOrderItemRepository.save(item);
        }

        return savedOrder;
    }
}
