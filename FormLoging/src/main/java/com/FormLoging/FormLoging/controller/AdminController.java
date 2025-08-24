package com.FormLoging.FormLoging.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/get")
    public ResponseEntity<String> get(){
        return new ResponseEntity<>("This is admin access controller ", HttpStatus.OK);
    }
}
