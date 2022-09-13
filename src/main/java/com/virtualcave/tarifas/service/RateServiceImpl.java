package com.virtualcave.tarifas.service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.virtualcave.tarifas.dto.Currency;
import com.virtualcave.tarifas.dto.RateDto;
import com.virtualcave.tarifas.dto.RateResponse;
import com.virtualcave.tarifas.entity.Rate;
import com.virtualcave.tarifas.exception.NotFoundException;
import com.virtualcave.tarifas.filter.RateFilter;
import com.virtualcave.tarifas.repository.RateRepository;
import com.virtualcave.tarifas.specification.RateSpecification;

@Service
public class RateServiceImpl implements RateService{

	@Autowired
	private RateRepository rateRepository;

	@Autowired
	private CurrencyService currencyService;
	
	@Override
	public RateDto save(RateDto rateDto) {
		Rate rate = Rate.builder()
				.id(rateDto.getId())
				.brandId(rateDto.getBrandId())
				.productId(rateDto.getProductId())
				.startDate(rateDto.getStartDate())
				.endDate(rateDto.getEndDate())
				.price(rateDto.getPrice())
				.currencyCode(rateDto.getCurrencyCode())
				.build();
		rateRepository.save(rate);
		rateDto.setId(rate.getId());
		return rateDto;
	}
	
	@Override
	public RateResponse findById(Long id) {
		Optional<Rate> optRate =  rateRepository.findById(id);
		if(optRate.isPresent()) {
			Rate rate = optRate.get();
			
			Currency currency = currencyService.getCurrencyByCode(rate.getCurrencyCode().name());
			
			return RateResponse.builder()
					.currencyCode(rate.getCurrencyCode())
					.symbol(currency.getSymbol())
					.price(this.formatPrice(rate.getPrice()))
					.build();
		}
		throw new NotFoundException("Entity not found with id: " + id);
	}
	
	private String formatPrice(Integer price) {
		NumberFormat nf = NumberFormat.getInstance(new Locale("es", "ES"));
		return nf.format(price).concat(",00");
	}
	
	@Override
	public Page<RateResponse> findAll(Pageable pageable) {
		Page<Rate> ratePage = rateRepository.findAll(pageable);
		
		List<RateResponse> response = ratePage.stream()
				.map(rate ->{
					Currency currency = currencyService.getCurrencyByCode(rate.getCurrencyCode().name());
					return RateResponse.builder()
							.currencyCode(rate.getCurrencyCode())
							.symbol(currency.getSymbol())
							.price(this.formatPrice(rate.getPrice()))
							.build();
				}).toList();
		
		return new PageImpl<>(response);
	}

	@Override
	public void deleteById(Long id) {
		try {
			rateRepository.deleteById(id);
		} catch (Exception e) {
			throw new NotFoundException("Entity not found with id: " + id, e);
		}
	}

	@Override
	public Page<Rate> findAllByFilters(RateFilter rateFilter, Pageable pageable) {
		return rateRepository.findAll(RateSpecification.filterBy(rateFilter), pageable);
	}

	@Override
	public RateDto update(Long id, RateDto rateDto) {
		
		if(rateRepository.findById(id).isPresent()) {
			Rate rate = Rate.builder()
					.id(rateDto.getId())
					.brandId(rateDto.getBrandId())
					.productId(rateDto.getProductId())
					.startDate(rateDto.getStartDate())
					.endDate(rateDto.getEndDate())
					.price(rateDto.getPrice())
					.currencyCode(rateDto.getCurrencyCode())
					.build();
			rateRepository.save(rate);
			return rateDto;
		}
		throw new NotFoundException("Entity does not exist with id: " + id);
	}


}
