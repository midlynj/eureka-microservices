package com.example.productservice.service;

import com.example.productservice.entity.Product;
import com.example.productservice.interfaces.ProductInterface;
import com.example.productservice.model.Coupon;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.restclients.CouponClient;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.json.bind.JsonbBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.Instant;

@Service
public class ProductServiceImpl implements ProductInterface {
    Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    CouponClient couponClient;

    @Autowired
    private ProductRepository productDAO;
    @Override
    public String healthCheck() {
        return "Product service up and running";
    }

    @Override
    @Retry(name = "product-api", fallbackMethod = "handleError")
    public Product create(Product product) {
        Coupon coupon = couponClient.getCoupon(product.getCouponCode());
        product.setPrice(product.getPrice().subtract(coupon.getDiscount()));

        LOGGER.info("New product created");

        return productDAO.save(product);
    }

    @Override
    public Product handleError(Product product, Exception exception) throws FileNotFoundException {
        LOGGER.error("Create Product failed. Saving Locally...");

        saveProductLocally(product);
        return product;
    }

    public void saveProductLocally(Product product) throws FileNotFoundException {
        String productFile = JsonbBuilder.create().toJson(product);//json representation of book object

        try (PrintWriter out = new PrintWriter("product-" + Instant.now().toEpochMilli() + ".json")) {
            out.println(productFile);
        }
    }
}
