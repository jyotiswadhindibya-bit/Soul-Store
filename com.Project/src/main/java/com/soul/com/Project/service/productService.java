package com.soul.com.Project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soul.com.Project.model.Product;
import com.soul.com.Project.repo.ProductRepo;

@Service
public class productService {
    
    private ProductRepo repo;
    public productService(ProductRepo r){
        repo=r;
    }
    

    public List<Product> getProducts(){
        return repo.findAll();
    }
}
