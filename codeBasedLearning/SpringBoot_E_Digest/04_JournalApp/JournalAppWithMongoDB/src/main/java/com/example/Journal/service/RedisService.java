package com.example.Journal.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.example.Journal.entity.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisService {
	
	  @Autowired
	    private StringRedisTemplate redisTemplate;

	    private final ObjectMapper objectMapper = new ObjectMapper();

	    private static final long CACHE_EXPIRATION = 5; // Cache expiration time in minutes

	    public WeatherResponse getCachedWeather(String city) {
	        try {
	            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
	            String cacheKey = "weather:" + city;
	            String cachedWeather = valueOps.get(cacheKey);

	            if (cachedWeather != null) {
	                log.info("Returning cached weather data for city: {} and the Response data: {}", city,cachedWeather);
	                return objectMapper.readValue(cachedWeather, WeatherResponse.class);
	            }
	        } catch (Exception e) {
	            log.error("Error retrieving weather data from cache", e);
	        }
	        return null;
	    }

	    public void cacheWeatherResponse(String city, WeatherResponse weatherResponse) {
	        try {
	            ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
	            String cacheKey = "weather:" + city;
	            valueOps.set(cacheKey, objectMapper.writeValueAsString(weatherResponse), CACHE_EXPIRATION, TimeUnit.MINUTES);
	            log.info("Weather data cached for city: {} for {} minutes", city, CACHE_EXPIRATION);
	        } catch (Exception e) {
	            log.error("Error saving weather data to cache", e);
	        }
	    }
}
