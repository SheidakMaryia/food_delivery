package com.example.food_delivery_diploma.controller;

import com.example.food_delivery_diploma.dto.restaurantDTO.RestAllArgsDTO;
import com.example.food_delivery_diploma.dto.restaurantDTO.RestNameCuisineAddressDTO;
import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Restaurant;
import com.example.food_delivery_diploma.service.RestaurantService;
import com.example.food_delivery_diploma.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping("/add")
    public ResponseEntity<String> addRestaurant(@Valid @RequestBody RestAllArgsDTO restAllArgsDTO){
        Restaurant restaurant = ConverterOfDTO.getRestAllArgsDTO(restAllArgsDTO);
        if (restaurantService.addRestaurant(restaurant)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRestaurantById(@PathVariable long id){
        if (restaurantService.deleteRestaurantById(id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Restaurant> getRestaurantById( @PathVariable long id){
        Optional<Restaurant> byId = restaurantService.getById(id);
        if (byId.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(byId.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable String name){
        Optional<Restaurant> restaurantByName = restaurantService.getRestaurantByName(name);
        if (restaurantByName.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(restaurantByName.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getByCuisine/{cuisine}")
    public ResponseEntity<List<Restaurant>> getRestaurantByCuisine(@PathVariable String cuisine){
        List<Restaurant> restaurantByCuisine = restaurantService.getRestaurantByCuisine(cuisine);
        if (restaurantByCuisine.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(restaurantByCuisine);
    }

    @PutMapping("/updRestaurant")
    public ResponseEntity<String> updRestaurantNameCuisineAddress(@Valid @RequestBody RestNameCuisineAddressDTO restNameCuisineAddressDTO, HttpSession httpSession){
        Restaurant restNameCuisineDTO1 = ConverterOfDTO.getRestNameCuisineAddressDTO(restNameCuisineAddressDTO);
        if (restaurantService.updateNameCuisineAddress(restNameCuisineDTO1)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/getAddress/{id}")
    public ResponseEntity<Address> getAddressByRestaurantId(@PathVariable long id){
        Optional<Address> address = restaurantService.getAddressesByRestaurantId(id);
        if (address.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(address.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        List<Restaurant> all = restaurantService.getAll();
        if (all.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(all);
    }

}
