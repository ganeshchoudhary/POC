package com.rupeek.poc.poc;

import com.rupeek.poc.poc.utils.VariableConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(VariableConfiguration.class)
public class PocApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PocApplication.class, args);
	}
	
}
