package com.example.respringboot.repositories;

import com.example.respringboot.model.UnitOfMeasurement;
import org.springframework.data.repository.CrudRepository;

public interface UOMRepository extends CrudRepository<UnitOfMeasurement,Long> {
}
