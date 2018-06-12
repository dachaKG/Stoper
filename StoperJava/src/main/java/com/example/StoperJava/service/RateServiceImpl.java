package com.example.StoperJava.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.StoperJava.model.Rate;


@Service
@Transactional
public class RateServiceImpl implements RateService {
	
	@Autowired
	RateRepo repository;

	@Override
	public List<Rate> findAll() {
		return (List<Rate>) repository.findAll();
	}

	@Override
	public Rate saveRate(Rate rate) {
		return repository.save(rate);
	}

	@Override
	public Optional<Rate> findOne(Long id) {
		return repository.findById(id);
	}

	@Override
	public List<Rate> findByEvaluatorId(Long id) {
		// TODO Auto-generated method stub
		return repository.findByEvaluatorId(id);
	}

	@Override
	public List<Rate> findByRecieverId(Long id) {
		// TODO Auto-generated method stub
		return repository.findByRecieverId(id);
	}

}
