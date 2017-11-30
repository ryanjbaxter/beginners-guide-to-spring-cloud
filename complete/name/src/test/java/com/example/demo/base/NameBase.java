package com.example.demo.base;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

import org.junit.Before;
import com.example.demo.NameController;
import com.example.demo.NameProperties;

/**
 * @author Ryan Baxter
 */
public class NameBase {

	@Before
	public void setup() {
		NameProperties nameProperties = new NameProperties();
		nameProperties.setName("Ryan");
		RestAssuredMockMvc.standaloneSetup(new NameController(nameProperties));
	}
}
