package com.virtualcave.tarifas.dto;

import com.virtualcave.tarifas.enums.CurrencyCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RateResponse {

	private CurrencyCode currencyCode;
	private String symbol;
	private String price;
}
