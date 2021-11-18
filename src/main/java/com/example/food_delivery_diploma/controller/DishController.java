package com.example.food_delivery_diploma.controller;

import com.example.food_delivery_diploma.dto.dishDTO.DishAllArgsDTO;
import com.example.food_delivery_diploma.dto.dishDTO.DishIdNameCategoryPriceDTO;
import com.example.food_delivery_diploma.dto.dishDTO.DishIdPriceDTO;
import com.example.food_delivery_diploma.entity.Dish;
import com.example.food_delivery_diploma.service.DishService;
import com.example.food_delivery_diploma.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("/dish")
public class DishController {

    private final DishService dishService;

    @PostMapping("/add")
    public ResponseEntity<String> addDish(@Valid @RequestBody DishAllArgsDTO dishAllArgsDTO){
        Dish dish = ConverterOfDTO.getDishAllArgsDTO(dishAllArgsDTO);
        if (dishService.addDish(dish)){
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Dish> getDishById( @PathVariable long id){
        Optional<Dish> dishById = dishService.getDishById(id);
        if (dishById.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(dishById.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getByCategory/{category}")
    public ResponseEntity<List<Dish>> getDishesByCategory( @PathVariable String category){
        List<Dish> dishesByCategory = dishService.getDishesByCategory(category);
        if (dishesByCategory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(dishesByCategory);
    }

    @GetMapping("/getByMaxPrice/{maxPrice}")
    public ResponseEntity<List<Dish>> getDishesByLimitOfPrice( @PathVariable Double maxPrice){
        List<Dish> dishes = dishService.getDishesByLimitOfPrice(maxPrice);
        if (dishes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(dishes);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDishById(@PathVariable long id){
        if (dishService.deleteDish(id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/updDish/{id}")
    public ResponseEntity<String> updDish(@Valid @RequestBody DishIdNameCategoryPriceDTO dishDTO, @PathVariable long id){
        Dish dish = ConverterOfDTO.getDishIdNameCategoryPriceDTO(dishDTO);
        if (dishService.updateDish(dish, id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PutMapping("/changePrice")
    public ResponseEntity<String> changePrice(@Valid @RequestBody DishIdPriceDTO dishIdPriceDTO){
        Dish dish = ConverterOfDTO.getDishIdPriceDTO(dishIdPriceDTO);
        if (dishService.changePrice(dish)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
