package com.virtualcave.tarifas.dto;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.virtualcave.tarifas.enums.CurrencyCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RateDto {

	private Long id;
	private Long brandId;
	private Long productId;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer price;
	@Enumerated(EnumType.STRING)
	private CurrencyCode currencyCode;
	
}
