package com.example.demo;

import reactor.core.publisher.Mono;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Ryan Baxter
 */
public class NameHandler {

	private static final Logger LOG = Logger.getLogger(NameHandler.class.getName());

	private WebClient webClient;

	public NameHandler(WebClient webClient) {
		this.webClient = webClient;
	}

	public Mono<String> getNameStringMono() {
		return webClient.get().uri("http://name").exchange().flatMap(resp -> resp.bodyToMono(String.class)).
				doOnSuccess(name -> LOG.log(Level.INFO, "Name: " + name));
	}

}
