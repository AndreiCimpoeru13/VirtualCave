package com.virtualcave.tarifas.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.virtualcave.tarifas.enums.CurrencyCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_RATES")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "BRAND_ID")
	private Long brandId;
	
	@Column(name = "PRODUCT_ID")
	private Long productId;
	
	@Column(name = "START_DATE")
	private LocalDate startDate;
	
	@Column(name = "END_DATE")
	private LocalDate endDate;
	
	@Column(name = "PRICE")
	private Integer price;
	
	@Column(name = "CURRENCY_CODE")
	@Enumerated(EnumType.STRING)
	private CurrencyCode currencyCode;
	

}
