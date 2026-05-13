package com.coding.resturant.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me")
public class DBController {
    @RequestMapping("/data")
    public String getData(){
        return "Hello World";
    }
}
