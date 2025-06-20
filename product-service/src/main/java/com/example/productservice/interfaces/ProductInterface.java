package com.example.productservice.interfaces;

import com.example.productservice.entity.Product;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.FileNotFoundException;

public interface ProductInterface {
    String healthCheck();
    Product create(@RequestBody Product product);
    Product handleError(Product product, Exception exception) throws FileNotFoundException;
}
