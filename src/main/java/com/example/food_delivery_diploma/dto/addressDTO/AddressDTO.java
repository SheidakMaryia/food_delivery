package com.example.food_delivery_diploma.dto.addressDTO;

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
public class AddressDTO {

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    private String city;

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    private String street;
    private int numberOfHouse;
    private float longitude;
    private float latitude;
}
