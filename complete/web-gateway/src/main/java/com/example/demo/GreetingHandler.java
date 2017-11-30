package com.example.demo;

import reactor.core.publisher.Mono;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Ryan Baxter
 */
public class GreetingHandler {
	private static final Logger LOG = Logger.getLogger(GreetingHandler.class.getName());


	private WebClient webClient;

	public GreetingHandler(WebClient webClient) {
		this.webClient = webClient;
	}

	public Mono<String> getGreetingStringMono() {
		return webClient.get().uri("http://greeting").exchange().flatMap(resp -> resp.bodyToMono(String.class))
				.doOnSuccess(greeting -> LOG.log(Level.INFO, "Greeting: " + greeting));
	}

	public Mono<String> getGreetingStringMono(String locale) {
		LOG.log(Level.INFO, "Locale: " + locale);

		return webClient.get().uri("http://greeting/" + locale).exchange().flatMap(resp -> resp.bodyToMono(String.class))
				.doOnSuccess(greeting -> LOG.log(Level.INFO, "Greeting: " +greeting));
	}
}
