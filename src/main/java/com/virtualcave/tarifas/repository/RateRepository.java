package com.virtualcave.tarifas.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.virtualcave.tarifas.entity.Rate;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long>{

	Page<Rate> findAll(Pageable pageable);

	Page<Rate> findAll(Specification<Rate> rateSpecification, Pageable pageable);
	
	Optional<Rate> findById(Long id);

}
