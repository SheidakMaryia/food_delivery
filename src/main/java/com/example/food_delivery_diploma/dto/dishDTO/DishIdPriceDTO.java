package com.example.food_delivery_diploma.dto.dishDTO;

import com.example.food_delivery_diploma.utils.ConstraintMessageManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishIdPriceDTO {

    private long id;

    private double price;
}
