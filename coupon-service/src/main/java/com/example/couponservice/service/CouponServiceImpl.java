package com.example.couponservice.service;

import com.example.couponservice.entity.Coupon;
import com.example.couponservice.interfaces.CouponInterface;
import com.example.couponservice.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponInterface {
    Logger LOGGER = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Autowired
    CouponRepository couponDAO;
    @Override
    public Coupon getCoupon(String code) {
        Optional<Coupon> couponOptional = Optional.ofNullable(couponDAO.findByCode(code));

        if (couponOptional.isEmpty()) {
            LOGGER.error("Coupon: " + code + " is invalid");
            throw new RuntimeException("Invalid coupon code: " + code);
        }

        return couponDAO.findByCode(code);
    }

    @Override
    public Coupon create(Coupon coupon) {
        LOGGER.info("New " + coupon + " created");
        return couponDAO.save(coupon);
    }

    @Override
    public String healthCheck() {
        return "Coupon service up and running";
    }
}
