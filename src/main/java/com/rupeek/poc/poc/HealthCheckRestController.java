package com.rupeek.poc.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckRestController {
	
	private VariableConfiguration variableConfiguration;
	
	@Value("${message}")
	String message;
	
	@Autowired
	public HealthCheckRestController(VariableConfiguration variableConfiguration) {
		this.variableConfiguration = variableConfiguration;
	}
	
	@GetMapping("/health")
	public ResponseEntity checkHealth(){
		System.out.println(variableConfiguration.userName);
		String message2 = "Ok" + variableConfiguration.userName + message ;
		return new ResponseEntity<>(message2, HttpStatus.OK);
	}
}
