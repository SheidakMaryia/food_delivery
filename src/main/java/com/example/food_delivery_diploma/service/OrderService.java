package com.example.food_delivery_diploma.service;

import com.example.food_delivery_diploma.dto.orderDTO.OrderDishIdAndUserIdDTO;
import com.example.food_delivery_diploma.entity.*;
import com.example.food_delivery_diploma.repository.DishRepository;
import com.example.food_delivery_diploma.repository.OrderRepository;
import com.example.food_delivery_diploma.repository.RestaurantRepository;
import com.example.food_delivery_diploma.repository.UserRepository;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    public Optional<Order> createOrder(OrderDishIdAndUserIdDTO orderDTO) {
        User user = userRepository.getById(orderDTO.getUserId());
        Optional<Dish> dishById = dishRepository.findById(orderDTO.getDishId());
        if (dishById.isPresent()){
            Dish dish = dishById.get();
            LocalTime orderTime = LocalTime.now();
            StatusOfOrder statusOfOrder = getStatusOfOrder(user, dish, orderTime);
            LocalTime arrivalTime = getArrivalTime(user, dish, orderTime);

            Order order = new Order();
            order.setUser(user);
            order.setDish(dish);
            order.setOrderTime(orderTime);
            order.setStatusOfOrder(statusOfOrder);
            order.setArrivalTime(arrivalTime);
            orderRepository.save(order);
            return Optional.of(order);
        }
        return Optional.empty();
    }

    public List<Order> getOrderByUserId(long userId) {
        return orderRepository.getOrdersByUserId(userId);
    }

    public boolean deleteOrderById(long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
            return true;
        } else {
            return false;
        }
    }

    public Optional<LocalTime> getArrivalTimeByUserId(long userId){
        return Optional.of(orderRepository.getById(userId).getArrivalTime());
    }

    private StatusOfOrder getStatusOfOrder(User user, Dish dish, LocalTime orderTime) {
        if (LocalTime.now().isBefore(orderTime.plusMinutes(20))) {
            return StatusOfOrder.PACKING;
        } else if (LocalTime.now().isAfter(orderTime.plusMinutes(20)) && LocalTime.now().isBefore(getArrivalTime(user,dish, orderTime))) {
            return StatusOfOrder.ONTHEWAY;
        } else return StatusOfOrder.DELIVERED;
    }

    private LocalTime getArrivalTime(User user, Dish dish, LocalTime orderTime) {
        long restaurantId = dish.getRestaurant().getId();
        long userId = user.getId();

        long timeForDelivery = calcTimeForDelivery(restaurantId, userId);
        LocalTime arrivalTime = orderTime.plusMinutes(timeForDelivery);
        return arrivalTime;
    }

    private long calcTimeForDelivery(long restaurantId, long userId) {
        double distance = calcDistance(restaurantId, userId);
        double averageSpeedOfCar = 50.0;

        double time = distance / averageSpeedOfCar;
        double timeInMins = time * 60;
        long timeForDelivery = (long) timeInMins;
        return timeForDelivery;
    }

    private double calcDistance(long restaurantId, long userId) {
        Address addressRest = restaurantRepository.getById(restaurantId).getAddress();
        Address addressUser = userRepository.getById(userId).getAddress();

        float latR = addressRest.getLatitude();
        float lngR = addressRest.getLongitude();
        float latU = addressUser.getLatitude();
        float lngU = addressUser.getLongitude();

        double earthRadius = 6371000;
        double dLat = Math.toRadians(latR - latU);
        double dLng = Math.toRadians(lngR - lngU);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(latU)) * Math.cos(Math.toRadians(latR)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c * 0.001;

        return dist;
    }
}
