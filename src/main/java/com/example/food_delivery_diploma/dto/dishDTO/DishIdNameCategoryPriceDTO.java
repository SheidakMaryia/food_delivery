package com.example.food_delivery_diploma.dto.dishDTO;

import com.example.food_delivery_diploma.dto.restaurantDTO.RestAllArgsDTO;
import com.example.food_delivery_diploma.dto.restaurantDTO.RestIdDTO;
import com.example.food_delivery_diploma.utils.ConstraintMessageManager;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DishIdNameCategoryPriceDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    private String name;

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    private String category;

    private long restId;

    private double price;
}
