package com.example.food_delivery_diploma.service;

import com.example.food_delivery_diploma.entity.Address;
import com.example.food_delivery_diploma.entity.Cuisine;
import com.example.food_delivery_diploma.entity.Restaurant;
import com.example.food_delivery_diploma.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public boolean addRestaurant(Restaurant restaurant){
        if (restaurantRepository.existsByNameAndAddress(restaurant.getName(), restaurant.getAddress())){
            return false;
        }else {
            restaurantRepository.save(restaurant);
            return true;
        }
    }

    public boolean deleteRestaurantById(long id){
        if (restaurantRepository.existsById(id)){
            restaurantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Restaurant> getById(long id) {
        return restaurantRepository.findById(id);
    }

    public Optional<Restaurant> getRestaurantByName(String name){
        return restaurantRepository.findByName(name);
    }

    public List<Restaurant> getRestaurantByCuisine(String cuisine){
        return restaurantRepository.getByCuisine(Cuisine.valueOf(cuisine.toUpperCase()));
    }

    public boolean updateNameCuisineAddress(Restaurant restNameCuisineAddress) {
        if (restaurantRepository.existsById(restNameCuisineAddress.getId())) {
            Restaurant byId = restaurantRepository.getById(restNameCuisineAddress.getId());
            byId.setName(restNameCuisineAddress.getName());
            byId.setCuisine(restNameCuisineAddress.getCuisine());
            byId.setAddress(restNameCuisineAddress.getAddress());
            restaurantRepository.save(restNameCuisineAddress);
            return true;
        }
        return false;
    }

    public Optional<Address> getAddressesByRestaurantId(long restaurantId){
        if (restaurantRepository.existsById(restaurantId)) {
            return Optional.of(restaurantRepository.getById(restaurantId).getAddress());
        }
        return Optional.empty();
    }

    public List<Restaurant> getAll(){
        return restaurantRepository.findAll();
    }

}
