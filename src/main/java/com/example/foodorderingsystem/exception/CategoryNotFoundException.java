package com.example.foodorderingsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends Exception{

    public CategoryNotFoundException(String message){
        super(message);
    }
}
