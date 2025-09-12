package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.model.*;
import com.example.hotelmanagement.repository.UserRepository;
import com.example.hotelmanagement.service.BookingService;
import com.example.hotelmanagement.service.CleaningService;
import com.example.hotelmanagement.service.FoodService;
import com.example.hotelmanagement.repository.BookingRepository;
import com.example.hotelmanagement.repository.MenuItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final BookingService bookingService;
    private final CleaningService cleaningService;
    private final FoodService foodService;
    private final BookingRepository bookingRepository;
    private final MenuItemRepository menuItemRepository;

    public UserController(UserRepository userRepository, BookingService bookingService, CleaningService cleaningService, FoodService foodService, BookingRepository bookingRepository, MenuItemRepository menuItemRepository) {
        this.userRepository = userRepository;
        this.bookingService = bookingService;
        this.cleaningService = cleaningService;
        this.foodService = foodService;
        this.bookingRepository = bookingRepository;
        this.menuItemRepository = menuItemRepository;
    }

    // ---- simple registration ----
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user){
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        user.setRole("USER");
        return ResponseEntity.ok(userRepository.save(user));
    }

    // ---- Book room ----
    @PostMapping("/bookings")
    public ResponseEntity<Booking> bookRoom(@RequestParam Long userId,
                                           @RequestParam Long roomId,
                                           @RequestParam String checkIn,
                                           @RequestParam String checkOut) {
        LocalDate in = LocalDate.parse(checkIn);
        LocalDate out = LocalDate.parse(checkOut);
        Booking b = bookingService.bookRoom(userId, roomId, in, out);
        return ResponseEntity.ok(b);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getBookings(@RequestParam Long userId){
        return ResponseEntity.ok(bookingService.getBookingsForUser(userId));
    }

    // ---- Cleaning request ----
    @PostMapping("/cleaning")
    public ResponseEntity<CleaningRequest> requestCleaning(@RequestParam Long bookingId, @RequestParam(required = false) String notes){
        return ResponseEntity.ok(cleaningService.requestCleaning(bookingId, notes));
    }

    // ---- Food ordering ----
    // itemIdToQty example in body: { "1": 2, "3": 1 }
    @PostMapping("/food")
    public ResponseEntity<FoodOrder> placeFoodOrder(@RequestParam Long userId,
                                                    @RequestParam(required = false) Long bookingId,
                                                    @RequestBody Map<Long,Integer> itemIdToQty) {
        FoodOrder order = foodService.placeOrder(userId, bookingId, itemIdToQty);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/menu")
    public ResponseEntity<List<MenuItem>> getMenu() {
        return ResponseEntity.ok(foodService.getMenu());
    }
}
