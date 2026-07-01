package com.soul.com.Project.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public Product getProductById(int id){
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product p,MultipartFile imageFile) throws IOException{
        p.setImageData(imageFile.getBytes());
        p.setImageName(imageFile.getOriginalFilename());
        p.setImageType((imageFile.getContentType()));
        return repo.save(p);
    }
    
    public Product updateProduct(int id ,Product p,MultipartFile imageFile) throws IOException{
        p.setImageData(imageFile.getBytes());
        p.setImageType((imageFile.getContentType()));
        p.setImageName(imageFile.getOriginalFilename());
        return repo.save(p);
    }

    public void deleteProduct(int id){
        repo.deleteById(id);
    }
}
