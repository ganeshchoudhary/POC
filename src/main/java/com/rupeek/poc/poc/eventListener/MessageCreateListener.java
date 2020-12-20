package com.rupeek.poc.poc.eventListener;

import com.rupeek.poc.poc.service.QueryService;
import discord4j.core.event.domain.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListener extends MessageListener implements EventListener<MessageCreateEvent> {
	QueryService queryService;
	
	@Autowired
	public MessageCreateListener(QueryService queryService) {
		super(queryService);
	}
	
	@Override
	public Class<MessageCreateEvent> getEventType() {
		return MessageCreateEvent.class;
	}
	
	@Override
	public Mono<Void> execute(MessageCreateEvent event) {
		return processCommand(event.getMessage());
	}
}