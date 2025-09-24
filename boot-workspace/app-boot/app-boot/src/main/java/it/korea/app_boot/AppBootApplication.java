package it.korea.app_boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AppBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppBootApplication.class, args);
	}

}
