package com.example.food_delivery_diploma.dto.telephoneDTO;

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
public class TelephoneDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)//читается только на выходе
    private long id;

    @NotBlank(message = ConstraintMessageManager.NOT_BLANK_ERROR)
    @Size(min = 7, max = 12, message = ConstraintMessageManager.PHONE_NUMBER_ERROR)
    private String number;
}