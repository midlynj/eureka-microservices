package com.example.productservice.controller;

import com.example.productservice.entity.Product;
import com.example.productservice.service.ProductServiceImpl;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/product", headers = "Accept=application/json")
@RefreshScope
public class ProductController {
    @Autowired
    ProductServiceImpl productService;

    @GetMapping(value="")
    public String healthCheck() {
        return productService.healthCheck();
    }


    @PostMapping(value = "/products")
//    @Retry(name = "product-api", fallbackMethod = "handleError")
    public Product create(@RequestBody Product product) {
        return productService.create(product);

    }
}

