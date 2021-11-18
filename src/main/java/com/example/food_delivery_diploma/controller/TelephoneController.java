package com.example.food_delivery_diploma.controller;

import com.example.food_delivery_diploma.dto.telephoneDTO.TelephoneDTO;
import com.example.food_delivery_diploma.entity.Telephone;
import com.example.food_delivery_diploma.service.TelephoneService;
import com.example.food_delivery_diploma.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("/telephone")
public class TelephoneController {

    private final TelephoneService telephoneService;

    @PostMapping("/add")
    public ResponseEntity<String> addTelephone(@Valid @RequestBody TelephoneDTO telephoneDTO){
        Telephone telephoneDTO1 = ConverterOfDTO.getTelephoneDTO(telephoneDTO);
        if (telephoneService.saveTelephone(telephoneDTO1)){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTelephoneById(@PathVariable long id){
        if (telephoneService.deleteTelephoneById(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getTelById/{id}")
    public ResponseEntity<Telephone> getTelephoneById(@PathVariable long id){
        Optional<Telephone> telephoneById = telephoneService.getTelephoneById(id);
        if (telephoneById.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(telephoneById.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
