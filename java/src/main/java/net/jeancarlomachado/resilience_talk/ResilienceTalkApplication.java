package net.jeancarlomachado.resilience_talk;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.function.Function;


@SpringBootApplication
public class ResilienceTalkApplication {
	private final RestTemplate restTemplate;

	public ResilienceTalkApplication() {
		this.restTemplate = new RestTemplateBuilder().build();
	}

	public static void main(String[] args) {
		new ResilienceTalkApplication().example();
	}

	private void example() {

		CircuitBreakerConfig config = CircuitBreakerConfig.custom().failureRateThreshold(20).build();
		CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
		CircuitBreaker circuitBreaker = registry.circuitBreaker("my");

		Function<String, String> requestResource = (String input) -> this.requestRemoteResource(input);

		Function<String, String>  decorated = CircuitBreaker.decorateFunction(circuitBreaker, requestResource);

		for (int i = 0; i < 10; i++) {
			decorated.apply("");
		}

	}

	private String requestRemoteResource(String conf) {
		System.out.println("Start requesting reco");

		final HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "curl/7.54.1");

		final HttpEntity<String> entity = new HttpEntity<String>(headers);

		String url = "http://httpstat.us/200";
		final ResponseEntity<String> result = this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		System.out.println(result.getBody());

		return result.getBody();
	}
}
