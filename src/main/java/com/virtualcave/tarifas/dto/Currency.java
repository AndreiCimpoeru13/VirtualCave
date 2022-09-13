package com.virtualcave.tarifas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Currency {

	private Long id;
	private String symbol;
	private String code;
	private Integer decimals;
}
