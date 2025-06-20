package com.example.couponservice.interfaces;

import com.example.couponservice.entity.Coupon;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CouponInterface {
    Coupon getCoupon( String code);
    Coupon create(@RequestBody Coupon coupon);
    String healthCheck();
}
