# HotelDB

hotel-management/
 ├── src/main/java/com/example/hotelmanagement/
 │    ├── controller/
 │    │    ├── AdminController.java
 │    │    ├── UserController.java
 │    ├── service/
 │    │    ├── RoomService.java
 │    │    ├── impl/RoomServiceImpl.java
 │    │    ├── BookingService.java
 │    │    ├── impl/BookingServiceImpl.java
 │    │    ├── CleaningService.java
 │    │    ├── impl/CleaningServiceImpl.java
 │    │    ├── FoodService.java
 │    │    ├── impl/FoodServiceImpl.java
 │    ├── repository/
 │    │    ├── RoomRepository.java
 │    │    ├── BookingRepository.java
 │    │    ├── CleaningRepository.java
 │    │    ├── MenuItemRepository.java
 │    │    ├── FoodOrderRepository.java
 │    │    ├── UserRepository.java
 │    ├── model/
 │    │    ├── Room.java
 │    │    ├── Booking.java
 │    │    ├── CleaningRequest.java
 │    │    ├── MenuItem.java
 │    │    ├── FoodOrder.java
 │    │    ├── FoodOrderItem.java
 │    │    ├── User.java
 │    ├── config/
 │    │    └── CorsConfig.java
 │    ├── exception/
 │    │    └── GlobalExceptionHandler.java
 │    └── HotelManagementApplication.java
 └── src/main/resources/
      ├── application.properties
      └── (optional) data.sql
