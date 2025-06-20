package com.example.productservice.restclients;

import com.example.productservice.model.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("ApiGateWay-Service")
public interface CouponClient {
    @GetMapping("/api/coupon/{code}")
    Coupon getCoupon(@PathVariable("code") String code);

}
