package com.example.food_delivery_diploma.dto.userDTO;

import com.example.food_delivery_diploma.dto.addressDTO.AddressDTO;
import com.example.food_delivery_diploma.dto.telephoneDTO.TelephoneDTO;
import com.example.food_delivery_diploma.utils.ConstraintMessageManager;
import com.example.food_delivery_diploma.utils.Patterns;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNameAddressTelDTO {
    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    private String name;

    private TelephoneDTO telephone;

    private AddressDTO address;
}
