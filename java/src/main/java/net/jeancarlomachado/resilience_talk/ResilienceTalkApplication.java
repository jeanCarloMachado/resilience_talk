package net.jeancarlomachado.resilience_talk;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class ResilienceTalkApplication {
	private final RestTemplate restTemplate;

	public ResilienceTalkApplication() {
		this.restTemplate = new RestTemplateBuilder().build();
	}

	public static void main(String[] args) {
		new ResilienceTalkApplication().requestReco();
	}

	private void requestReco() {
		System.out.println("Start requesting reco");

		final HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "curl/7.54.1");

		final HttpEntity<String> entity = new HttpEntity<String>(headers);

		String url = "http://httpstat.us/200";
		final ResponseEntity<String> result = this.restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		System.out.println(result.getBody());
	}
}
