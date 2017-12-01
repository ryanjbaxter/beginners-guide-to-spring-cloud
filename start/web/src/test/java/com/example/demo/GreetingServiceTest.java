package com.example.demo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

/**
 * @author Ryan Baxter
 */
public class GreetingServiceTest {

	private GreetingService greetingService;

	@Before
	public void setup() {
		RestTemplate rest = mock(RestTemplate.class);
		doReturn("Hello").when(rest).getForObject(eq("http://localhost:9090"), eq(String.class));
		doReturn("Hello").when(rest).getForObject(eq("http://localhost:9090/en"), eq(String.class));
		doReturn("Hola").when(rest).getForObject(eq("http://localhost:9090/es"), eq(String.class));
		greetingService = new GreetingService(rest);
	}

	@After
	public void teardown() {
		greetingService = null;
	}

	@Test
	public void getGreeting() throws Exception {
		assertEquals("Hello", greetingService.getGreeting());
	}

	@Test
	public void getGreetingWithLocale() throws Exception {
		assertEquals("Hello", greetingService.getGreeting("en"));
		assertEquals("Hola", greetingService.getGreeting("es"));
	}

}