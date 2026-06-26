package com.soul.com.Project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.soul.com.Project.model.*;
import com.soul.com.Project.service.productService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    
    @RequestMapping("/")
    public String greet(){
        return "Welcome to the store";
    }


    private productService service;
    public ProductController(productService s){
        service=s;
    }

    @GetMapping("/products")
    public List<Product> getProducts(){
        return service.getProducts();
    }
}
