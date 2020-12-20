package com.rupeek.poc.poc.Dto;

import java.util.List;

public class QueryResponseDto {
	
	private List<SearchQueryDto> items;
	
	public QueryResponseDto() {
	}
	
	public QueryResponseDto(List<SearchQueryDto> items) {
		this.items = items;
	}
	
	public List<SearchQueryDto> getItems() {
		return items;
	}
	
	public void setItems(List<SearchQueryDto> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		return "QueryResponseDto{" +
				"items=" + items +
				'}';
	}
}
