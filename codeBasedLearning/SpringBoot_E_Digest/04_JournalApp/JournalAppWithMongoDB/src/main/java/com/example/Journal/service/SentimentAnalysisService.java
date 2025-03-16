package com.example.Journal.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SentimentAnalysisService {
	
	 private static final String API_URL = "https://api.openai.com/v1/chat/completions";
	 
	 @Value("${chat.api.key:AnyValueToStopNullfailure}")
	 private String API_KEY;

	public String getSentimentAnalysis(String text) {
		 

		        RestTemplate restTemplate = new RestTemplate();

		        HttpHeaders headers = new HttpHeaders();
		        headers.setContentType(MediaType.APPLICATION_JSON);
		        headers.setBearerAuth(API_KEY);

		        // JSON Request Body for ChatGPT API
		        String requestBody = """
		        {
		          "model": "gpt-4o",
		          "messages": [
		            {"role": "system", "content": "Analyze the sentiment of the given text and return the response stating the sentiments of writer and praise him for his writing style this is actually journal entries of a user and the reponse should be based on that this will be directly reaching to the user through email."},
		            {"role": "user", "content": "%s"}
		          ],
		          "max_tokens": 300
		        }
		        """.formatted(text);

		        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

		        // Call OpenAI API
		        ResponseEntity<Map> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, Map.class);
		        
		        // Extract the response
		        Map<String, Object> responseBody = response.getBody();
		        if (responseBody != null && responseBody.containsKey("choices")) {
		            Map<String, Object> choice = (Map<String, Object>) ((java.util.List<?>) responseBody.get("choices")).get(0);
		            Map<String, Object> message = (Map<String, Object>) choice.get("message");
		            return message.get("content").toString();
		        }
		        return "Error analyzing sentiment";
		    }
}
