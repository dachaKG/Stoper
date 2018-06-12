package com.example.StoperJava.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.StoperJava.model.Rate;

public interface RateRepo extends CrudRepository<Rate, Long>{

	
	public List<Rate> findByEvaluatorId(Long evaluatorId);
	
	public List<Rate> findByRecieverId(Long recieverId);
}
