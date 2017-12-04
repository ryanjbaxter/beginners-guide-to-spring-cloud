package com.example.demo;

import reactor.core.publisher.Mono;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
@EnableDiscoveryClient
public class WebSpringCloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSpringCloudGatewayApplication.class, args);
	}

	@Configuration
	static class GatewayConfiguration {
		@Bean
		public RouterFunction<ServerResponse> monoRouterFunction(NameHandler nameHandler, GreetingHandler greetingHandler) {
			return route(GET("/"), serverRequest -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).body(
							greetingHandler.getGreetingStringMono(serverRequest.headers().asHttpHeaders().
									getAcceptLanguageAsLocales().get(0).toLanguageTag()).concatWith(Mono.just(" ")).
									concatWith(nameHandler.getNameStringMono()), String.class));
		}

		@Bean
		public Sampler sampler() {
			return new AlwaysSampler();
		}

		@Bean
		//TODO the LoadBalanceExchangeFilterFunction can be removed once we have a load balanced WebClient
		public WebClient webClient(WebClient.Builder webClientBuilder, LoadBalancerExchangeFilterFunction lbef) {
			return webClientBuilder.filter(lbef).build();
		}

		@Bean
		public NameHandler nameHandler(WebClient webClient, LoadBalancerClient lbc) {
			return new NameHandler(webClient);
		}

		@Bean
		public GreetingHandler greetingHandler(WebClient webClient, LoadBalancerClient lbc) {
			return new GreetingHandler(webClient);
		}
	}
}
