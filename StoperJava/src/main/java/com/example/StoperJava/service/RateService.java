package com.example.StoperJava.service;

import java.util.List;
import java.util.Optional;

import com.example.StoperJava.model.Rate;

public interface RateService {

    public List<Rate> findAll();
	
	public Rate saveRate(Rate rate);
	
	public Optional<Rate> findOne(Long id);
	
	public List<Rate> findByEvaluatorId(Long id);
	
	public List<Rate> findByRecieverId(Long id);
}
