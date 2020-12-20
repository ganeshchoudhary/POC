package com.rupeek.poc.poc.eventListener;

import com.rupeek.poc.poc.service.QueryService;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public abstract class MessageListener {
	
	QueryService queryService;
	private String query;
	private String result;
	
	@Autowired
	public MessageListener(QueryService queryService) {
		this.queryService = queryService;
	}
	
	/**
	 * To process the event
	 * @param eventMessage event
	 * @return response
	 */
	public Mono<Void> processCommand(Message eventMessage) {
		return Mono.just(eventMessage)
				.filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
				.filter(message -> {
					if (message.getContent().length() > 7 && message.getContent().substring(0, 7).contains("!google")) {
						query = message.getContent().substring(8);
						result = queryService.getQueryResultInString(query.toLowerCase());
						return true;
					} else if (message.getContent().length() > 7 && message.getContent().substring(0, 7).contains("!recent")) {
						query = message.getContent().substring(8);
						result = queryService.getHistory(query.toLowerCase());
						return true;
					} else if (message.getContent().length() == 3 && message.getContent().substring(0, 3).contains("hey")) {
						result = "hi";
						return true;
					} else {
						return false;
					}
					
				})
				.flatMap(Message::getChannel)
				.flatMap(channel -> channel.createMessage(result))
				.then();
	}
}