package com.virtualcave.tarifas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.virtualcave.tarifas.dto.RateDto;
import com.virtualcave.tarifas.dto.RateResponse;
import com.virtualcave.tarifas.entity.Rate;
import com.virtualcave.tarifas.filter.RateFilter;

public interface RateService {

	Page<RateResponse> findAll(Pageable pageable);
	
	Page<Rate> findAllByFilters(RateFilter rateFilter, Pageable pageable);

	RateResponse findById(Long id);
	
	RateDto save(RateDto rate);
	
	RateDto update(Long id, RateDto rate);
	
	void deleteById(Long id);
	
}
