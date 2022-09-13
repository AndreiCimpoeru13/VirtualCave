package com.virtualcave.tarifas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.virtualcave.tarifas.dto.RateDto;
import com.virtualcave.tarifas.dto.RateResponse;
import com.virtualcave.tarifas.enums.CurrencyCode;
import com.virtualcave.tarifas.service.RateService;

class RateControllerTest {

	@InjectMocks
	RateController rateControllerMock;
	
	@Mock
	RateService rateServiceMock;
	
	RateResponse rateResponse;
	RateDto rateDto;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		
		rateResponse = RateResponse.builder()
				.price("1.000,57")
				.currencyCode(CurrencyCode.EUR)
				.symbol("€")
				.build();
		
		rateDto = RateDto.builder()
			.id(1L)
			.brandId(2L)
			.productId(2L)
			.startDate(LocalDate.of(2022, 9, 13))
			.endDate(LocalDate.of(2022, 9, 20))
			.price(15000)
			.currencyCode(CurrencyCode.EUR)
			.build();
	}
	
	@Test
	void saveTest() {
		when(rateServiceMock.save(any(RateDto.class))).thenReturn(rateDto);
		
		ResponseEntity<RateDto> respEntity = rateControllerMock.save(rateDto);
		RateDto resp = respEntity.getBody();
		
		verify(rateServiceMock).save(any(RateDto.class));
		assertEquals(HttpStatus.CREATED, respEntity.getStatusCode());
		assertEquals(rateDto, resp);
	}
	
	@Test
	void findByIdTest() {
		when(rateServiceMock.findById(anyLong())).thenReturn(rateResponse);
		
		ResponseEntity<RateResponse> respEntity = rateControllerMock.getById(anyLong());
		RateResponse resp = respEntity.getBody();
		
		verify(rateServiceMock).findById(anyLong());
		assertEquals("€", resp.getSymbol());
		assertEquals("1.000,57", resp.getPrice());
		assertEquals(CurrencyCode.EUR, resp.getCurrencyCode());
		assertEquals(HttpStatus.OK, respEntity.getStatusCode());
	}
	
	@Test
	void deleteByIdTest() {
		ResponseEntity<Void> respEntity = rateControllerMock.deleteById(anyLong());
		
		verify(rateServiceMock).deleteById(anyLong());
		assertEquals(HttpStatus.NO_CONTENT, respEntity.getStatusCode());
	}
	
	@Test
	void updateRateTest() {
		when(rateServiceMock.update(anyLong(), any(RateDto.class))).thenReturn(rateDto);
		RateDto rateDto2 = RateDto.builder()
				.id(1L)
				.price(10000)
				.build();
		ResponseEntity<RateDto> respEntity = rateControllerMock.updateRate(1L, rateDto2);
		RateDto resp = respEntity.getBody();
		
		verify(rateServiceMock).update(anyLong(), any(RateDto.class));
		assertEquals(HttpStatus.NO_CONTENT, respEntity.getStatusCode());
		assertNotEquals(rateDto2.getPrice(), resp.getPrice());
	}
}
