package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Ryan Baxter
 */
@Service
public class GreetingService {
	private static final String URL = "http://localhost:9090";
	private RestTemplate rest;

	public GreetingService(RestTemplate rest) {
		this.rest = rest;
	}

	public String getGreeting() {
		return rest.getForObject(URL, String.class);
	}

	public String getGreeting(String locale) {
		return rest.getForObject(new StringBuilder().append(URL).append("/").append(locale).toString(), String.class);
	}
}
