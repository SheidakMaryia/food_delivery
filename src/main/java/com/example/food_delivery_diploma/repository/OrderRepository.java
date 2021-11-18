package com.example.food_delivery_diploma.repository;

import com.example.food_delivery_diploma.entity.Order;
import com.example.food_delivery_diploma.entity.StatusOfOrder;
import com.example.food_delivery_diploma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByStatusOfOrder(StatusOfOrder statusOfOrder);

    @Query(value = "FROM Order WHERE user.id =:id")
    List<Order> getOrdersByUserId(long id);

}
