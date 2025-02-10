package crocobob.CIENderella;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CIENderellaApplication {
	public static void main(String[] args) {
		SpringApplication.run(CIENderellaApplication.class, args);
	}
}