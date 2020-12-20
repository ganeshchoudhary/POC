package com.rupeek.poc.poc.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class QueryEntity {
	
	@Id
	private String id;
	
	private String title;
	
	private List<String> links = new ArrayList<>();
	
	public QueryEntity() {
	}
	
	public QueryEntity(String id, String title, List<String> links) {
		this.id = id;
		this.title = title;
		this.links = links;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<String> getLinks() {
		return links;
	}
	
	public void setLinks(List<String> links) {
		this.links = links;
	}
	
	public void addLink(String link) {
		this.links.add(link);
	}
}