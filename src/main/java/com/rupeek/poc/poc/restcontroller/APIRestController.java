package com.rupeek.poc.poc.restcontroller;

import com.rupeek.poc.poc.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIRestController {
	
	private QueryService queryService;
	
	@Autowired
	public APIRestController(QueryService queryService) {
		this.queryService = queryService;
	}
	
	@GetMapping("/health")
	public ResponseEntity checkHealth() {
		System.out.println("Health check");
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}
	
	/**
	 * Get api for google search
	 * @param query query parameters
	 * @return response with google result
	 */
	@GetMapping("/get-result")
	public ResponseEntity getSearchResult(@RequestParam("query") String query) {
		
		return new ResponseEntity<>(queryService.getQueryResult(query.toLowerCase()), HttpStatus.OK);
	}
	
	/**
	 * To get the search history
	 * @param query query parameter
	 * @return response with searched history
	 */
	@GetMapping("get-history")
	public ResponseEntity getHistory(@RequestParam("query") String query) {
		return new ResponseEntity<>(queryService.getHistory(query.toLowerCase()), HttpStatus.OK);
	}
}
