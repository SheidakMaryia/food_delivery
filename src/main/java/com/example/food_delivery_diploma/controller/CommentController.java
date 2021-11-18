package com.example.food_delivery_diploma.controller;

import com.example.food_delivery_diploma.dto.commentDishDTO.ComAllArgsDTO;
import com.example.food_delivery_diploma.dto.commentDishDTO.ComIdTypeDTO;
import com.example.food_delivery_diploma.entity.Comment;
import com.example.food_delivery_diploma.entity.User;
import com.example.food_delivery_diploma.service.CommentService;
import com.example.food_delivery_diploma.utils.ConverterOfDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@Transactional
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<String> saveComment(@Valid @RequestBody ComAllArgsDTO comDTO){
        Comment comment = commentService.saveComment(ConverterOfDTO.getComAllArgsDTO(comDTO));
        if (comment == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{commId}/{userId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commId, @PathVariable long userId, HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (commentService.deleteComment(commId, userId, user)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<Comment>> getCommentsOfDishOrRest(@Valid @RequestBody ComIdTypeDTO comIdTypeDTO){
        List<Comment> comments = commentService.getComments(comIdTypeDTO);
        if (comments.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }
}
