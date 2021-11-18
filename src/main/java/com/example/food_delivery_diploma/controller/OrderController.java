package com.example.food_delivery_diploma.controller;

import com.example.food_delivery_diploma.dto.orderDTO.OrderDishIdAndUserIdDTO;
import com.example.food_delivery_diploma.entity.Order;
import com.example.food_delivery_diploma.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDishIdAndUserIdDTO orderDTO){
        Optional<Order> order1 = orderService.createOrder(orderDTO);
        if (order1.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(order1.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/getByUserId/{id}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable long id){
        List<Order> orderByUserId = orderService.getOrderByUserId(id);
        if (orderByUserId.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return new ResponseEntity<>(orderByUserId, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable long id){
        if (orderService.deleteOrderById(id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getArrivalTimeByUserId/{id}")
    public ResponseEntity<LocalTime> getArrivalTimeByUserId(@PathVariable long id){
        Optional<LocalTime> arrivalTimeByUserId = orderService.getArrivalTimeByUserId(id);
        if (arrivalTimeByUserId.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(arrivalTimeByUserId.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
