package com.example.hotelmanagement.controller;

import com.example.hotelmanagement.model.*;
import com.example.hotelmanagement.repository.*;
import com.example.hotelmanagement.service.BookingService;
import com.example.hotelmanagement.service.CleaningService;
import com.example.hotelmanagement.service.RoomService;
import com.example.hotelmanagement.service.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final RoomService roomService;
    private final BookingService bookingService;
    private final CleaningService cleaningService;
    private final MenuItemRepository menuItemRepository;
    private final FoodService foodService;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public AdminController(RoomService roomService, BookingService bookingService, CleaningService cleaningService, MenuItemRepository menuItemRepository, FoodService foodService, UserRepository userRepository, BookingRepository bookingRepository) {
        this.roomService = roomService;
        this.bookingService = bookingService;
        this.cleaningService = cleaningService;
        this.menuItemRepository = menuItemRepository;
        this.foodService = foodService;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    // ---- Rooms ----
    @PostMapping("/rooms")
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room){
        return ResponseEntity.ok(roomService.updateRoom(id, room));
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> listRooms(){
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    // ---- Bookings ----
    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> allBookings(){
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // ---- Cleaning ----
    @GetMapping("/cleanings")
    public ResponseEntity<List<CleaningRequest>> getCleanings(){
        return ResponseEntity.ok(cleaningService.getAllCleaningRequests());
    }

    @PutMapping("/cleanings/{id}/status")
    public ResponseEntity<CleaningRequest> updateCleaningStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(cleaningService.updateStatus(id, status));
    }

    // ---- Menu management ----
    @PostMapping("/menu")
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem item){
        return ResponseEntity.ok(menuItemRepository.save(item));
    }

    @PutMapping("/menu/{id}")
    public ResponseEntity<MenuItem> updateMenuItem(@PathVariable Long id, @RequestBody MenuItem item){
        MenuItem existing = menuItemRepository.findById(id).orElseThrow(() -> new RuntimeException("Menu item not found"));
        existing.setName(item.getName());
        existing.setDescription(item.getDescription());
        existing.setPrice(item.getPrice());
        existing.setAvailable(item.getAvailable());
        return ResponseEntity.ok(menuItemRepository.save(existing));
    }

    @GetMapping("/menu")
    public ResponseEntity<List<MenuItem>> getMenu(){
        return ResponseEntity.ok(foodService.getMenu());
    }
}
