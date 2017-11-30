package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author Ryan Baxter
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.cloud.config.enabled:false"})
@AutoConfigureStubRunner(ids = {"com.example:greeting:+:stubs:0"}, workOffline = true)
@DirtiesContext
public class GreetingServiceTest {

	@Autowired
	GreetingService greetingService;

	@Test
	public void getGreeting() throws Exception {
		assertEquals("Hello", greetingService.getGreeting());
	}

	@Test
	public void getGreetingWithLocale() throws Exception {
		String greeting = greetingService.getGreeting("en");
		assertEquals("Hello", greeting);
		greeting = greetingService.getGreeting("es");
		assertEquals("Hola", greeting);
		greeting = greetingService.getGreeting("de");
		assertEquals("Hallo", greeting);
	}

}