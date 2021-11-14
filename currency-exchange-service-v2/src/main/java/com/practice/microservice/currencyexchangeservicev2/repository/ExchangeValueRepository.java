package com.practice.microservice.currencyexchangeservicev2.repository;

import com.practice.microservice.currencyexchangeservicev2.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    @Query("SELECT a FROM ExchangeValue a where upper(a.from) = upper(:from) and upper(a.to) = upper(:to)")
    ExchangeValue findByCurrencies(@Param("from") String from, @Param("to") String to);
}
