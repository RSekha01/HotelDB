package com.example.hotelmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "food_order_items")
public class FoodOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private FoodOrder foodOrder;

    @ManyToOne
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    private int quantity;

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public FoodOrder getFoodOrder() { return foodOrder; }
    public void setFoodOrder(FoodOrder foodOrder) { this.foodOrder = foodOrder; }

    public MenuItem getMenuItem() { return menuItem; }
    public void setMenuItem(MenuItem menuItem) { this.menuItem = menuItem; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
