package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.cloud.config.enabled:false"})
@AutoConfigureStubRunner(ids = {"com.example:name:+:stubs:0","com.example:greeting:+:stubs:0"}, workOffline = true)
@DirtiesContext
public class WebApplicationTests {

	@Autowired
	TestRestTemplate rest;

	@Test
	public void contextLoads() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept-Language", "es");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<String> greetingResp = rest.exchange("/", HttpMethod.GET, entity, String.class);
		assertEquals("Hola Ryan", greetingResp.getBody());
	}
}
