package com.example.food_delivery_diploma.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ExcResponseDTO {//standard response for exceptions
    private LocalDateTime time;
    private int status;
    private String error;
    private String message;
    private String pathURI;
}
