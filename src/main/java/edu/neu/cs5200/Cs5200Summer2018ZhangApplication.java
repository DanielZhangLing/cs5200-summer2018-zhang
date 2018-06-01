package edu.neu.cs5200;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Cs5200Summer2018ZhangApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
		return application.sources(
				Cs5200Summer2018ZhangApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Cs5200Summer2018ZhangApplication.class, args);
	}
}
