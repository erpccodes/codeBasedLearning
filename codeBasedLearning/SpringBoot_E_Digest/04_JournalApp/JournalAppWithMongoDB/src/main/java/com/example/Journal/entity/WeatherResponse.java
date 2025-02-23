package com.example.Journal.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


@Data
public class WeatherResponse {

    private Main main;
   
    @JsonProperty("name")
    private String city; // This corresponds to the "name" property in JSON

    
    
    @Data
    public static class Main {
        
    	@JsonProperty("temp")
    	private double temptemperature;
        
        @JsonProperty("feels_like")
        private double feelsLike;  // Notice the underscore to match JSON

		@Override
		public String toString() {
			return "temptemperature=" + temptemperature + ", feels_likfeelsLikee=" + feelsLike;
		}
        
        
    }



	@Override
	public String toString() {
		return "[" + main + ", city=" + city + "]";
	}
	    

}
