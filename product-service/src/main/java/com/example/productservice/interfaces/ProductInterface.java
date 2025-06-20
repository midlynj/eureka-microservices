package com.example.productservice.interfaces;

import com.example.productservice.entity.Product;
import org.springframework.web.bind.annotation.RequestBody;

public interface ProductInterface {
    public Product create(@RequestBody Product product);
    Product handleError(Product product, Exception exception);
}
