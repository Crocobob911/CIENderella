package crocobob.CIENderella;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class CIENderellaApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CIENderellaApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

		log.info("<< Application started >>");
	}
}