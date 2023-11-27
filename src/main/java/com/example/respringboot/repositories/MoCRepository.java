package com.example.respringboot.repositories;

import com.example.respringboot.model.MethodOfCalculation;
import org.springframework.data.repository.CrudRepository;

public interface MoCRepository extends CrudRepository<MethodOfCalculation,Long> {
}
