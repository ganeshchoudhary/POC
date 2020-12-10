package com.rupeek.poc.poc;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(prefix = "rupeek")
@Component
@NoArgsConstructor
@Data
public class VariableConfiguration {
	
	public String userName;
	
	public int userId;
}
