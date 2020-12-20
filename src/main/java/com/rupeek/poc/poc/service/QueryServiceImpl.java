package com.rupeek.poc.poc.service;

import com.rupeek.poc.poc.Dto.QueryResponseDto;
import com.rupeek.poc.poc.entity.QueryEntity;
import com.rupeek.poc.poc.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {
	
	private QueryRepository queryRepository;
	
	@Autowired
	public QueryServiceImpl(QueryRepository queryRepository) {
		this.queryRepository = queryRepository;
	}
	
	private final WebClient webClient = WebClient.builder()
			.baseUrl("https://www.googleapis.com")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
			.build();
	
	/**
	 * To get result from api request
	 *
	 * @param query query parameter
	 * @return response
	 */
	public QueryResponseDto getQueryResult(String query) {
		System.out.println("Making request to google.");
		
		QueryResponseDto queryResponseDto = webClient.get().uri("/customsearch/v1?key=AIzaSyCqISRklR7IMTzpwfmS6UoG9MFiWW5R-Q4&cx=2bddb746f733e38e4&q=apple&count=5")
				.retrieve()
				.bodyToMono(QueryResponseDto.class)
				.block();
		StringBuilder stringBuilder = new StringBuilder();
		QueryEntity queryEntity = new QueryEntity();
		queryEntity.setTitle(query.toLowerCase());
		for (int i = 0; i < 5; i++) {
			queryEntity.addLink(queryResponseDto.getItems().get(i).getLink());
			stringBuilder.append(i + ". " + queryResponseDto.getItems().get(i).getLink() + " \n");
		}
		queryRepository.save(queryEntity);
		return queryResponseDto;
		
	}
	
	/**
	 * get query result for bot
	 *
	 * @param query query parameter
	 * @return response string
	 */
	public String getQueryResultInString(String query) {
		System.out.println("Making request to google.");
		
		QueryResponseDto queryResponseDto = webClient.get().uri("/customsearch/v1?key=AIzaSyCqISRklR7IMTzpwfmS6UoG9MFiWW5R-Q4&cx=2bddb746f733e38e4&q=" + query + "&count=5")
				.retrieve()
				.bodyToMono(QueryResponseDto.class)
				.block();
		StringBuilder stringBuilder = new StringBuilder();
		QueryEntity queryEntity = new QueryEntity();
		queryEntity.setTitle(query.toLowerCase());
		for (int i = 0; i < 5; i++) {
			queryEntity.addLink(queryResponseDto.getItems().get(i).getLink());
			stringBuilder.append(i + ". " + queryResponseDto.getItems().get(i).getLink() + " \n");
		}
		queryRepository.save(queryEntity);
		return stringBuilder.toString();
		
	}
	
	/**
	 * To get history
	 *
	 * @param query query parameter
	 * @return response string
	 */
	@Override
	public String getHistory(String query) {
		List<QueryEntity> queryEntities = queryRepository.findFirst5ByTitleContainingIgnoreCase(query);
		StringBuilder stringBuilder = new StringBuilder();
		for (QueryEntity queryEntity : queryEntities) {
			stringBuilder.append(queryEntity.getTitle() + " \n");
		}
		return stringBuilder.toString();
	}
	
	
}
