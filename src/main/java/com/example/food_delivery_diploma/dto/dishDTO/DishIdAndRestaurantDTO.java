package com.example.food_delivery_diploma.dto.dishDTO;

import com.example.food_delivery_diploma.dto.restaurantDTO.RestAllArgsDTO;
import com.example.food_delivery_diploma.utils.ConstraintMessageManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishIdAndRestaurantDTO {
    private long dishId;
    private RestAllArgsDTO restaurant;
}
