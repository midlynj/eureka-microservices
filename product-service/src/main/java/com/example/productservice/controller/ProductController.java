package com.example.productservice.controller;

import com.example.productservice.model.Coupon;
import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.restclients.CouponClient;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.json.bind.JsonbBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping(value = "/api/product", headers = "Accept=application/json")
@RefreshScope
public class ProductController {

    @Autowired
    CouponClient couponClient;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(value="")
    public String healthCheck() {
        return "Product service up and running";
    }


    @PostMapping(value = "/products")
    @Retry(name = "product-api", fallbackMethod = "handleError")
    public Product create(@RequestBody Product product) {
        Coupon coupon = couponClient.getCoupon(product.getCouponCode());
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));
        return productRepository.save(product);

    }

    public void saveProductLocally(Product product) throws FileNotFoundException {
        String productFile = JsonbBuilder.create().toJson(product);//json representation of book object
        try (PrintWriter out = new PrintWriter("product-" + Instant.now().toEpochMilli() + ".json")) {
            out.println(productFile);
        }
        }

    public Product handleError(Product product, Exception exception) throws FileNotFoundException {
        System.out.println("Inside Handle Error");
        saveProductLocally(product);
        return product;
    }
}

