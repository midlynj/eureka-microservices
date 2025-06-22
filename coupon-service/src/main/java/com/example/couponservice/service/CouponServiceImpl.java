package com.example.couponservice.service;

import com.example.couponservice.entity.Coupon;
import com.example.couponservice.interfaces.CouponInterface;
import com.example.couponservice.repository.CouponRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponInterface {
    Logger LOGGER = LoggerFactory.getLogger(CouponServiceImpl.class);

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    CacheManager cacheManager;

    @Autowired
    CouponRepository couponDAO;
    @Override
    @Cacheable(value = "coupon", key = "#code")
    public Coupon getCoupon(String code) {
        Optional<Coupon> couponOptional = Optional.ofNullable(couponDAO.findByCode(code));

        if (couponOptional.isEmpty()) {
            LOGGER.error("Coupon: " + code + " is invalid");
            throw new RuntimeException("Invalid coupon code: " + code);
        }

//        if (redisTemplate.hasKey(code))
//            return (Coupon) redisTemplate.opsForValue().get(code);

        System.out.println("pulling from the database");
//        redisTemplate.opsForValue().set(code,couponOptional.get());
        return couponOptional.get();
    }

    @Override
    public Coupon create(Coupon coupon) {
        LOGGER.info("New " + coupon + " created");
        return couponDAO.save(coupon);
    }
    @Override
    public String healthCheck() {
        cacheManager.getCache("coupon").invalidate();//ran into issue with @CacheEvict this is the work around to invalidating cache
        return "Coupon service up and running";
    }
}
