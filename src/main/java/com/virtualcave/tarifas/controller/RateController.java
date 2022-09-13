package com.virtualcave.tarifas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtualcave.tarifas.dto.RateDto;
import com.virtualcave.tarifas.dto.RateResponse;
import com.virtualcave.tarifas.entity.Rate;
import com.virtualcave.tarifas.filter.RateFilter;
import com.virtualcave.tarifas.service.RateService;


@RestController
@RequestMapping("/v1/rates")
public class RateController {
	
	private static final String ID = "id";
	
	@Autowired
	private RateService rateService;
	
	@PostMapping
	public ResponseEntity<RateDto> save(@RequestBody RateDto rate){
		return new ResponseEntity<>(rateService.save(rate), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RateResponse> getById(@PathVariable Long id){
		return new ResponseEntity<>(rateService.findById(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		rateService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<RateDto> updateRate(@PathVariable Long id,  @RequestBody RateDto rate){
		return new ResponseEntity<>(rateService.update(id, rate), HttpStatus.NO_CONTENT);
	}
	
	@GetMapping
	public ResponseEntity<Page<RateResponse>> findAll(
			@PageableDefault(size = 20, sort = ID) Pageable pageable){
		return new ResponseEntity<>(rateService.findAll(pageable), HttpStatus.OK);
	}
	
	@PostMapping("/filter")
	public ResponseEntity<Page<Rate>> findAllByFilters(
			@PageableDefault(size = 20, sort = ID) Pageable pageable,
			@RequestBody(required = false) RateFilter rateFilter){
		return new ResponseEntity<>(rateService.findAllByFilters(rateFilter, pageable), HttpStatus.OK);
	}
	
}
