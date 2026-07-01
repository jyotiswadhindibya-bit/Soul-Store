package com.soul.com.Project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(service.getProducts(),HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> geProduct(@PathVariable int id){
        Product p=service.getProductById(id);
        if(p!=null) return new ResponseEntity<>(p,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,@RequestPart MultipartFile imageFile){
        try {
            Product pro=service.addProduct(product,imageFile);
            return new ResponseEntity<>(pro,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageBbyProductId(@PathVariable int productId){
        Product product=service.getProductById(productId);
        byte[] imageFile=product.getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product,@RequestPart MultipartFile imageFile){
        Product p=null;
        try {
             p=service.updateProduct(id,product,imageFile);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Update!",HttpStatus.BAD_REQUEST);
        }
        if(p!=null) return new ResponseEntity<>("Updated",HttpStatus.OK);
        else return new ResponseEntity<>("Failed to Update!",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        Product p=service.getProductById(id);
        if(p!=null){
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }

}
