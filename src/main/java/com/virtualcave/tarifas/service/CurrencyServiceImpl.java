package com.virtualcave.tarifas.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.virtualcave.tarifas.dto.Currency;

@Service
public class CurrencyServiceImpl implements CurrencyService{

	private static final String CURRENCIES = "currencies/";
	private static final String BASE_URL = "http://localhost:9090/v1/";
	
	private final RestTemplate restTemplate = new RestTemplate();

	
	@Override
	public List<Currency> getCurrencies() {
		return Arrays.asList(restTemplate.getForObject(BASE_URL + CURRENCIES, Currency[].class));
	}

	@Override
	public Currency getCurrencyByCode(String code) {
		return restTemplate.getForObject(BASE_URL + CURRENCIES + code, Currency.class);
	}
	
	
}
