package com.virtualcave.tarifas.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.virtualcave.tarifas.entity.Rate;
import com.virtualcave.tarifas.filter.RateFilter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RateSpecification {

	private static final String ID = "id";
	private static final String BRAND_ID = "brandId";
	private static final String PRODUCT_ID = "productId";
	private static final String START_DATE = "startDate";
	private static final String END_DATE = "endDate";
	private static final String PRICE = "price";
	private static final String CURRENCY_CODE = "currencyCode";
	
	private RateSpecification() {
	    throw new IllegalStateException("Specification class");
	 }
	
	public static Specification<Rate> filterBy(final RateFilter rateFilter){
		return ((root, query, cb)->{
			List<Predicate> predicates = new ArrayList<>();
			
			if(rateFilter == null) return cb.and(predicates.toArray(new Predicate[] {}));
			
			addEqualPredicate(ID, rateFilter.getId(), root, cb, predicates);
			addEqualPredicate(PRODUCT_ID, rateFilter.getProductId(), root, cb, predicates);
			addEqualPredicate(BRAND_ID, rateFilter.getBrandId(), root, cb, predicates);
			addEqualPredicate(START_DATE, rateFilter.getStartDate(), root, cb, predicates);
			addEqualPredicate(END_DATE, rateFilter.getEndDate(), root, cb, predicates);
			addEqualPredicate(PRICE, rateFilter.getPrice(), root, cb, predicates);
			addEqualPredicate(CURRENCY_CODE, rateFilter.getCurrencyCode(), root, cb, predicates);
			
			return cb.and(predicates.toArray(new Predicate[] {}));
		});
	}
	
	private static <T, S> void addEqualPredicate(String rootField, T filterField, Root<S> root, CriteriaBuilder cb, List<Predicate> predicates) {
		if(filterField != null) {
			predicates.add(cb.equal(root.get(rootField), filterField));
		}
	}
}
