package com.rupeek.poc.poc.repository;

import com.rupeek.poc.poc.entity.QueryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface QueryRepository extends MongoRepository<QueryEntity, String> {
	
	List<QueryEntity> findFirst5ByTitleContainingIgnoreCase(@RequestParam("title") String title);
	
}