package com.example.food_delivery_diploma.dto.orderDTO;

import com.example.food_delivery_diploma.dto.dishDTO.DishIdDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserIdDTO;
import com.example.food_delivery_diploma.entity.Dish;
import com.example.food_delivery_diploma.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishIdAndUserIdDTO {

    private long dishId;
    private long userId;
}
