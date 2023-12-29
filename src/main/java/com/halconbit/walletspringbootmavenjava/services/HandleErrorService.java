package com.halconbit.walletspringbootmavenjava.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.HashMap;


@Service
public class HandleErrorService {
    public ResponseEntity<?> validate(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errorsMap = new HashMap<String, String>();
            for(FieldError fieldError : bindingResult.getFieldErrors()) {
                errorsMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorsMap, HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}