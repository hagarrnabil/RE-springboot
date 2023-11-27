package com.example.respringboot.repositories;

import com.example.respringboot.model.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<Currency,Long> {
}
