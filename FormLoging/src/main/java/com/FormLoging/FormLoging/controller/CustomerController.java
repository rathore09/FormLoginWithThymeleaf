package com.FormLoging.FormLoging.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("get")
    public ResponseEntity<String> get(){
        return  ResponseEntity.status(HttpStatus.OK).body("This is user access controller");
    }

}
