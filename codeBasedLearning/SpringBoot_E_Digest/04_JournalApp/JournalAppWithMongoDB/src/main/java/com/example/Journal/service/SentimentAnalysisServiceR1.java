package com.example.Journal.service;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SentimentAnalysisServiceR1 {
	
	 private static final String API_URL = "http://localhost:11434/api/generate"; // Local Deepseek-R1 API

		public String getSentimentAnalysisR1(String text) {
			 
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			// JSON Request Body for Deepseek-R1 API
			String requestBody = String.format(
				    "{ \"model\": \"deepseek-r1:1.5b\", \"prompt\": \"Analyze the sentiment of the following text: '%s' and return the response stating the sentiments of writer like his mood and praise him for his writing style. This is actually journal entries of a user.\", \"stream\": false }",
				    text
				);
			
			log.info("request body: "+requestBody);

			HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

			// Call Deepseek-R1 API
			ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, Map.class);
			
			// Extract the response
			Map<String, Object> responseBody = response.getBody();
			if (responseBody != null && responseBody.containsKey("response")) {
				String rawResponse = responseBody.get("response").toString();
				String cleanedResponse=cleanResponse(rawResponse);
				log.info("cleaned Response: "+cleanedResponse);
	            return cleanedResponse;  // Remove <think> tags
			}
			return "Error analyzing sentiment";
		}
		
		  private String cleanResponse(String response) {
			  return response.replaceAll("(?s)<think>.*?</think>", "").trim();
		    }
}
