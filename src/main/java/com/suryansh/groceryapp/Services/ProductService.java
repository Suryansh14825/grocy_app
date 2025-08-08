package com.suryansh.groceryapp.Services;

import com.suryansh.groceryapp.Model.Product;
import com.suryansh.groceryapp.Repository.ProductRepository;

import java.util.List;

public class ProductService {
    private ProductRepository productRepository=new ProductRepository();
    public List<Product> getallProducts(){
        return productRepository.getallProduct();
    }

    public boolean addProduct(Product product){
        return productRepository.addProduct(product);
    }

    public boolean updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    public boolean deleteProduct(int productId) {
        return productRepository.deleteProduct(productId);
    }

}

