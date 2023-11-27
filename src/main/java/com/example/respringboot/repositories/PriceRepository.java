package com.example.respringboot.repositories;

import com.example.respringboot.model.PriceType;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<PriceType,Long> {
}
