package com.example.food_delivery_diploma.utils;

import com.example.food_delivery_diploma.dto.addressDTO.AddressDTO;
import com.example.food_delivery_diploma.dto.commentDishDTO.ComAllArgsDTO;
import com.example.food_delivery_diploma.dto.commentDishDTO.ComIdTypeDTO;
import com.example.food_delivery_diploma.dto.dishDTO.DishAllArgsDTO;
import com.example.food_delivery_diploma.dto.dishDTO.DishIdAndRestaurantDTO;
import com.example.food_delivery_diploma.dto.dishDTO.DishIdNameCategoryPriceDTO;
import com.example.food_delivery_diploma.dto.dishDTO.DishIdPriceDTO;
import com.example.food_delivery_diploma.dto.orderDTO.OrderDishUserDTO;
import com.example.food_delivery_diploma.dto.restaurantDTO.RestAllArgsDTO;
import com.example.food_delivery_diploma.dto.restaurantDTO.RestIdDTO;
import com.example.food_delivery_diploma.dto.restaurantDTO.RestNameCuisineAddressDTO;
import com.example.food_delivery_diploma.dto.telephoneDTO.TelephoneDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserAllArgsDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserAuthDTO;
import com.example.food_delivery_diploma.dto.userDTO.UserNameAddressTelDTO;
import com.example.food_delivery_diploma.entity.*;

public class ConverterOfDTO {

    //USER
    public static User getAllArgsUserDTO(UserAllArgsDTO userAllArgsDTO){
        return User.builder()
                .name(userAllArgsDTO.getName())
                .username(userAllArgsDTO.getUsername())
                .password(userAllArgsDTO.getPassword())
                .telephone(Telephone.builder()
                        .number(userAllArgsDTO.getTelephone().getNumber())
                        .build())
                .role(Role.USER)
                .address(Address.builder()
                        .city(userAllArgsDTO.getAddress().getCity())
                        .street(userAllArgsDTO.getAddress().getStreet())
                        .numOfHouse(userAllArgsDTO.getAddress().getNumberOfHouse())
                        .longitude(userAllArgsDTO.getAddress().getLongitude())
                        .latitude(userAllArgsDTO.getAddress().getLatitude())
                        .build())
                .build();
    }

    public static User getUserNameAddressTelDTO(UserNameAddressTelDTO userNameAddressTelDTO){
        return User.builder()
                .name(userNameAddressTelDTO.getName())
                .telephone(Telephone.builder()
                        .number(userNameAddressTelDTO.getTelephone().getNumber())
                        .build())
                .role(Role.USER)
                .address(Address.builder()
                        .city(userNameAddressTelDTO.getAddress().getCity())
                        .street(userNameAddressTelDTO.getAddress().getStreet())
                        .numOfHouse(userNameAddressTelDTO.getAddress().getNumberOfHouse())
                        .longitude(userNameAddressTelDTO.getAddress().getLongitude())
                        .latitude(userNameAddressTelDTO.getAddress().getLatitude())
                        .build())
                .build();
    }

    public static User getUserAuthDTO(UserAuthDTO userAuthDTO){
        return User.builder()
                .username(userAuthDTO.getUsername())
                .password(userAuthDTO.getPassword())
                .build();
    }

    // TELEPHONE
    public static Telephone getTelephoneDTO(TelephoneDTO telephoneDTO){
        return Telephone.builder()
                .number(telephoneDTO.getNumber())
                .build();
    }

    //ADDRESS
    public static Address getAddressDTO(AddressDTO addressDTO){
        return Address.builder()
                .city(addressDTO.getCity())
                .street(addressDTO.getStreet())
                .numOfHouse(addressDTO.getNumberOfHouse())
                .longitude(addressDTO.getLongitude())
                .latitude(addressDTO.getLatitude())
                .build();
    }

    //RESTAURANT
    public static Restaurant getRestAllArgsDTO(RestAllArgsDTO restAllArgsDTO){
        return Restaurant.builder()
                .name(restAllArgsDTO.getName())
                .cuisine(Cuisine.valueOf(restAllArgsDTO.getCuisine().toUpperCase()))
                .address(Address.builder()
                        .city(restAllArgsDTO.getAddress().getCity())
                        .street(restAllArgsDTO.getAddress().getStreet())
                        .numOfHouse(restAllArgsDTO.getAddress().getNumberOfHouse())
                        .longitude(restAllArgsDTO.getAddress().getLongitude())
                        .latitude(restAllArgsDTO.getAddress().getLatitude())
                        .build())
                .build();
    }

    public static Restaurant getRestNameCuisineAddressDTO(RestNameCuisineAddressDTO restNameCuisineAddressDTO){
        return Restaurant.builder()
                .id(restNameCuisineAddressDTO.getId())
                .name(restNameCuisineAddressDTO.getName())
                .cuisine(Cuisine.valueOf(restNameCuisineAddressDTO.getCuisine().toUpperCase()))
                .address(Address.builder()
                        .city(restNameCuisineAddressDTO.getAddress().getCity())
                        .street(restNameCuisineAddressDTO.getAddress().getStreet())
                        .numOfHouse(restNameCuisineAddressDTO.getAddress().getNumberOfHouse())
                        .longitude(restNameCuisineAddressDTO.getAddress().getLongitude())
                        .latitude(restNameCuisineAddressDTO.getAddress().getLatitude())
                        .build())
                .build();
    }

    public static Restaurant getRestIdDTO(RestIdDTO restIdDTO){
        return Restaurant.builder()
                .id(restIdDTO.getRestId())
                .build();
    }


    //DISH
    public static Dish getDishAllArgsDTO(DishAllArgsDTO dishAllArgsDTO){
        return Dish.builder()
                .name(dishAllArgsDTO.getName())
                .category(Category.valueOf(dishAllArgsDTO.getCategory().toUpperCase()))
                .restaurant(Restaurant.builder()
                    .id(dishAllArgsDTO.getRestIdDTO().getRestId())
                    .build())
                .price(dishAllArgsDTO.getPrice())
                .build();
    }

    public static Dish getDishIdNameCategoryPriceDTO(DishIdNameCategoryPriceDTO dish){
        return Dish.builder()
                .name(dish.getName())
                .category(Category.valueOf(dish.getCategory().toUpperCase()))
                .restaurant(Restaurant.builder()
                    .id(dish.getRestId())
                    .build())
                .price(dish.getPrice())
                .build();
    }

    public static Dish getDishIdAndRestaurantDTO(DishIdAndRestaurantDTO dishIdAndRestaurantDTO){
        return Dish.builder()
                .id(dishIdAndRestaurantDTO.getDishId())
                .restaurant(Restaurant.builder()
                        .name(dishIdAndRestaurantDTO.getRestaurant().getName())
                        .cuisine(Cuisine.valueOf(dishIdAndRestaurantDTO.getRestaurant().getCuisine().toUpperCase()))
                        .address(Address.builder()
                                .city(dishIdAndRestaurantDTO.getRestaurant().getAddress().getCity())
                                .street(dishIdAndRestaurantDTO.getRestaurant().getAddress().getStreet())
                                .numOfHouse(dishIdAndRestaurantDTO.getRestaurant().getAddress().getNumberOfHouse())
                                .longitude(dishIdAndRestaurantDTO.getRestaurant().getAddress().getLongitude())
                                .latitude(dishIdAndRestaurantDTO.getRestaurant().getAddress().getLatitude())
                                .build())
                        .build())
                .build();
    }

    public static Dish getDishIdPriceDTO(DishIdPriceDTO dish){
        return Dish.builder()
                .id(dish.getId())
                .price(dish.getPrice())
                .build();
    }

    //ORDER
    public static Order getOrderDishUserDTO(OrderDishUserDTO orderDTO){
        return Order.builder()
                .dish(Dish.builder()
                        .name(orderDTO.getDish().getName())
                        .category(Category.valueOf(orderDTO.getDish().getCategory().toUpperCase()))
                        .restaurant(Restaurant.builder()
                                .id(orderDTO.getDish().getRestIdDTO().getRestId())
                                .build())
                        .price(orderDTO.getDish().getPrice())
                        .build())
                .user(User.builder()
                        .name(orderDTO.getUser().getName())
                        .username(orderDTO.getUser().getUsername())
                        .password(orderDTO.getUser().getPassword())
                        .telephone(Telephone.builder()
                                .number(orderDTO.getUser().getTelephone().getNumber())
                                .build())
                        .role(Role.USER)
                        .address(Address.builder()
                                .city(orderDTO.getUser().getAddress().getCity())
                                .street(orderDTO.getUser().getAddress().getStreet())
                                .numOfHouse(orderDTO.getUser().getAddress().getNumberOfHouse())
                                .longitude(orderDTO.getUser().getAddress().getLongitude())
                                .latitude(orderDTO.getUser().getAddress().getLatitude())
                                .build())
                        .build())
                .build();
    }


    //COMMENT
    public static Comment getComAllArgsDTO(ComAllArgsDTO dto){
        if (dto.getType().equals(TypeOfComment.DISH)){
            return CommentDish.builder()
                    .dish(Dish.builder()
                            .id(dto.getId())
                            .build())
                    .description(dto.getDescription())
                    .user(User.builder()
                            .id(dto.getUser())
                            .build())
                    .build();
        }else {
            return CommentRestaurant.builder()
                    .restaurant(Restaurant.builder()
                            .id(dto.getId())
                            .build())
                    .description(dto.getDescription())
                    .user(User.builder()
                            .id(dto.getUser())
                            .build())
                    .build();
        }
    }

    public static Comment getComIdTypeDTO(ComIdTypeDTO dto){
        if (dto.getType().equals(TypeOfComment.DISH)){
            return CommentDish.builder()
                    .dish(Dish.builder()
                            .id(dto.getId())
                            .build())
                    .build();
        }else {
            return CommentRestaurant.builder()
                    .restaurant(Restaurant.builder()
                            .id(dto.getId())
                            .build())
                    .build();
        }
    }

//    public static List<Comment> getListCommentsForResp(List<Comment> comments){
//        return List.of()
//    }
}
