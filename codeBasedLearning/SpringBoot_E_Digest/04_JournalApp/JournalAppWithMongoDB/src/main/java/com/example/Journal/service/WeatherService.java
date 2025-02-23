package com.example.Journal.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Journal.entity.WeatherResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WeatherService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	 @Value("${weather.api.key}") // API Key from application.properties
	    private String apiKey;
	 
	 @Value("${ipinfo.token}")
     private String token;
	 
	 private static final String BASE_URL ="https://api.openweathermap.org/data/2.5/weather";
	 
	  public WeatherResponse getCurrentWeather(HttpServletRequest request){
		  
	        log.info("IP:" +request.getRemoteAddr());
	        String ip = request.getRemoteAddr();
	        Map<String, Object> ipresponse=new HashMap<>();
	        if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
	        	  String path= "http://ip-api.com/json/";
	        	 ipresponse = restTemplate.getForObject(path, Map.class);
	        	 ip=(String) ipresponse.get("query");
	        }
	        
	        log.info("Last IP:" +ip);
	        
	        
	        String ipinfo=String.format("https://ipinfo.io/%s?token=",ip,token);
	        ipresponse = restTemplate.getForObject(ipinfo, Map.class);
	        
            String city= (String) ipresponse.get("city");
            log.info("City: " +city);
            String url = String.format("%s?q=%s&appid=%s&units=metric", BASE_URL, city, apiKey);
            log.info("FINAL URL: " + url);	
	
	        // Use exchange() to make the API call
	        ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET,null, WeatherResponse.class);
	        // Extract required fields
	        log.info("response: " + response);

	        log.info("WeatherResponse: {}", response.getBody());
	        
	        return response.getBody();
}
}
