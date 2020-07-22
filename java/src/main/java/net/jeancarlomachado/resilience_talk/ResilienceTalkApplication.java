package net.jeancarlomachado.resilience_talk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@SpringBootApplication
public class ResilienceTalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResilienceTalkApplication.class, args);
	}

	@RestController
	private static class TheController {
		@RequestMapping("/")
		public String handle () {
			return "message from rest handler";
		}

	}
}
