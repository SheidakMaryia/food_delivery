package com.example.food_delivery_diploma.aspect;

import com.example.food_delivery_diploma.dto.commentDishDTO.ComAllArgsDTO;
import com.example.food_delivery_diploma.dto.commentDishDTO.ComIdTypeDTO;
import com.example.food_delivery_diploma.dto.dishDTO.DishAllArgsDTO;
import com.example.food_delivery_diploma.entity.Comment;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class CommentLogAspect {
    private final Logger logger = LoggerFactory.getLogger(CommentLogAspect.class.getSimpleName());


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.CommentController.saveComment(..)) && args(comDTO, ..))")
    public void saveComment(ComAllArgsDTO comDTO){}

    @AfterReturning(value = "saveComment(comDTO)", argNames = "comDTO, returningValue", returning = "returningValue")
    public void logOfSaveComment(ComAllArgsDTO comDTO, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.CREATED)){
            logger.info("New comment ' {} ' added.", comDTO.getDescription());
        }else{
            logger.info("Comment ' {} ' added unsuccessfully", comDTO.getDescription());
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.CommentController.deleteComment(..)) && args(id, ..))")
    public void deleteComment(long id){}

    @AfterReturning(value = "deleteComment(id)", argNames = "id, returningValue", returning = "returningValue")
    public void logOfDeleteComment(long id, ResponseEntity<String> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Comment with id = {} successfully deleted.", id);
        }else{
            logger.info("Comment with id = {} deletion failed!", id);
        }
    }


    @Pointcut("execution(public * com.example.food_delivery_diploma.controller.CommentController.getCommentsOfDishOrRest(..)) && args(comIdTypeDTO, ..))")
    public void getCommentsOfDishOrRest(ComIdTypeDTO comIdTypeDTO){}

    @AfterReturning(value = "getCommentsOfDishOrRest(comIdTypeDTO)", argNames = "comIdTypeDTO, returningValue", returning = "returningValue")
    public void logOfGetCommentsOfDishOrRest(ComIdTypeDTO comIdTypeDTO, ResponseEntity<List<Comment>> returningValue){
        if (returningValue.getStatusCode().equals(HttpStatus.OK)){
            logger.info("Comment of {} successfully found.", comIdTypeDTO.getType());
        }else{
            logger.info("Comment of = {} not found!", comIdTypeDTO.getType());
        }
    }
}
