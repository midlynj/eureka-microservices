package com.example.couponservice.controller;

import com.example.couponservice.entity.Coupon;
import com.example.couponservice.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/coupon", headers = "Accept=application/json")
public class CouponController {
Logger LOGGER = LoggerFactory.getLogger(CouponController.class);
    @Autowired
    CouponRepository couponRepository;

    @GetMapping(value="")
    public String healthCheck() {
        return "Coupon service up and running";
    }

    @PostMapping(value="/create")
    public Coupon create(@RequestBody Coupon coupon) {

        return couponRepository.save(coupon);
    }

    @GetMapping(value="/{couponCode}")
    public Coupon getCoupon(@PathVariable("couponCode") String code) {
//        System.out.println("Server 1");
        Optional<Coupon> couponOptional = Optional.ofNullable(couponRepository.findByCode(code));
        if (couponOptional.isEmpty()) {
            LOGGER.error("coupon: " + code + " is invalid");
            throw new RuntimeException("bad coupon");
        }
        LOGGER.info("coupon: " + code + " found");

        return couponRepository.findByCode(code);
    }
}
