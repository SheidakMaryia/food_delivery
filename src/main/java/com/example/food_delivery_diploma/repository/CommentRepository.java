package com.example.food_delivery_diploma.repository;

import com.example.food_delivery_diploma.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "FROM Comment WHERE user.id =:userId")
    boolean existsByUserId(long userId);

    Optional<Comment> findByIdAndDescription(long id, String description);

    @Query(value = "FROM CommentDish WHERE dish.id =:dishId")
    List<Comment> getAllByDishId(long dishId);

    @Query(value = "FROM CommentRestaurant WHERE restaurant.id =:restId")
    List<Comment> getAllByRestaurantId(long restId);
}
