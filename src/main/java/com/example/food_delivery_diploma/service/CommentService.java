package com.example.food_delivery_diploma.service;


import com.example.food_delivery_diploma.dto.commentDishDTO.ComIdTypeDTO;
import com.example.food_delivery_diploma.entity.*;
import com.example.food_delivery_diploma.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public Comment saveComment(Comment comment){
        return commentRepository.save(comment);
    }

    public boolean deleteComment(long commentId, long userId, User user){
        if (commentRepository.existsById(commentId) && user.getId() == userId){
            commentRepository.deleteById(commentId);
            return true;
        }else {
            return false;
        }
    }

    public List<Comment> getComments(ComIdTypeDTO comIdTypeDTO){
        TypeOfComment type = comIdTypeDTO.getType();
        if (type.equals(TypeOfComment.DISH)){
            List<Comment> commentsByDishId = commentRepository.getAllByDishId(comIdTypeDTO.getId());
            return commentsByDishId;
        }else {
            List<Comment> commentsByRestaurantId = commentRepository.getAllByRestaurantId(comIdTypeDTO.getId());
            return commentsByRestaurantId;
        }
    }
}
