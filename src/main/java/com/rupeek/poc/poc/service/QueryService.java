package com.rupeek.poc.poc.service;

import com.rupeek.poc.poc.Dto.QueryResponseDto;

public interface QueryService {
	
	QueryResponseDto getQueryResult(String query);
	
	String getQueryResultInString(String query);
	
	String getHistory(String query);
}
