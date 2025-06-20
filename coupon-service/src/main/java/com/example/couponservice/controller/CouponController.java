package com.example.couponservice.controller;

import com.example.couponservice.entity.Coupon;
import com.example.couponservice.repository.CouponRepository;
import com.example.couponservice.service.CouponServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/coupon", headers = "Accept=application/json")
public class CouponController {
Logger LOGGER = LoggerFactory.getLogger(CouponController.class);
    @Autowired
    CouponServiceImpl couponService;

    @GetMapping(value="")
    public String healthCheck() {
        return couponService.healthCheck();
    }

    @PostMapping(value="/create")
    public Coupon create(@RequestBody Coupon coupon) {
        return couponService.create(coupon);
    }

    @GetMapping(value="/{couponCode}")
    public Coupon getCoupon(@PathVariable("couponCode") String code) {
        return couponService.getCoupon(code);
    }
}
