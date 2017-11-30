package com.example.demo.base;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import com.example.demo.GreetingController;
import com.example.demo.GreetingProperties;

/**
 * @author Ryan Baxter
 */
public class GreetingBase {

	@Before
	public void setup() {
		GreetingProperties greetingProperties = new GreetingProperties();
		greetingProperties.setGreeting("Hello");
		Map<String, String> greetings = new HashMap<>();
		greetings.put("EN", "Hello");
		greetings.put("ES", "Hola");
		greetings.put("DE", "Hallo");
		greetingProperties.setGreetings(greetings);

		RestAssuredMockMvc.standaloneSetup(new GreetingController(greetingProperties));
	}
}
