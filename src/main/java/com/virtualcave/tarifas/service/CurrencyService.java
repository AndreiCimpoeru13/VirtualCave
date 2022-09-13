package com.virtualcave.tarifas.service;

import java.util.List;

import com.virtualcave.tarifas.dto.Currency;

public interface CurrencyService {

	List<Currency> getCurrencies();

	Currency getCurrencyByCode(String code);
	
}
