package com.example.couponservice.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisClientConfig;

import java.time.Duration;

@Configuration
public class RedisConfig {
//
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory(
        new RedisStandaloneConfiguration("redis", 6379));
    }

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
////        uses default configuration
//        return new JedisConnectionFactory();
//    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setConnectionFactory(jedisConnectionFactory());
        template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }

//    @Bean
//    public RedisCacheConfiguration cacheConfiguration() {
//        return RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ofSeconds(2));// Set TTL to 10 minutes
////                .disableCachingNullValues();
//    }

}