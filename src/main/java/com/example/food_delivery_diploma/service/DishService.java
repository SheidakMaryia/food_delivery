package com.example.food_delivery_diploma.service;

import com.example.food_delivery_diploma.entity.Category;
import com.example.food_delivery_diploma.entity.Dish;
import com.example.food_delivery_diploma.entity.Restaurant;
import com.example.food_delivery_diploma.repository.DishRepository;
import com.example.food_delivery_diploma.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public boolean addDish(Dish dish){
        if (dishRepository.existsByNameAndCategory(dish.getName(), dish.getCategory())){
            return false;
        }else{
            Restaurant byId = restaurantRepository.getById(dish.getRestaurant().getId());
            dish.setRestaurant(byId);
            dishRepository.save(dish);
            return true;
        }
    }

    public boolean updateDish(Dish dish, long id){
        if (dishRepository.existsById(id)){
            dish.setId(id);
            dishRepository.save(dish);
            return true;
        }else{
            return false;
        }
    }

    public boolean deleteDish(long id){
        if (dishRepository.existsById(id)){
            dishRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    public boolean changePrice(Dish dish){
        if (dishRepository.existsById(dish.getId())){
            Dish byId = dishRepository.getById(dish.getId());
            byId.setPrice(dish.getPrice());
            dishRepository.save(byId);
            return true;
        }else{
            return false;
        }
    }

    public Optional<Dish> getDishById(long dishId){
        if (dishRepository.existsById(dishId)){
            return dishRepository.findById(dishId);
        }
        return Optional.empty();
    }

    public List<Dish> getDishesByCategory(String category){
        return dishRepository.getAllByCategory(Category.valueOf(category.toUpperCase()));
    }

    public List<Dish> getDishesByLimitOfPrice(double maxPrice){
        return dishRepository.getByPrice(maxPrice);
    }
}
