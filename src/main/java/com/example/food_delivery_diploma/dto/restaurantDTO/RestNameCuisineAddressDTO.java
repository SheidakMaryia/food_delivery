package com.example.food_delivery_diploma.dto.restaurantDTO;

import com.example.food_delivery_diploma.dto.addressDTO.AddressDTO;
import com.example.food_delivery_diploma.utils.ConstraintMessageManager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestNameCuisineAddressDTO {

    private long id;
    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    private String name;

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    private String cuisine;

    private AddressDTO address;
}
