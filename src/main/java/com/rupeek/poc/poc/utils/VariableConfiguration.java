package com.rupeek.poc.poc.utils;

import com.rupeek.poc.poc.eventListener.EventListener;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
@ConfigurationProperties
@Component
@NoArgsConstructor
@Data
public class VariableConfiguration {
	
	
	@Value("${token}")
	private String token;
	
	
	@Bean
	public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<EventListener<T>> eventListeners) {
		GatewayDiscordClient client = DiscordClientBuilder.create(token)
				.build()
				.login()
				.block();
		
		for (EventListener<T> listener : eventListeners) {
			client.on(listener.getEventType())
					.flatMap(listener::execute)
					.onErrorResume(listener::handleError)
					.subscribe();
		}
		
		return client;
	}
}
