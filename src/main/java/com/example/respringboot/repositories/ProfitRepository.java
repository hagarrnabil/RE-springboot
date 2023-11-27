package com.example.respringboot.repositories;

import com.example.respringboot.model.Profit;
import org.springframework.data.repository.CrudRepository;

public interface ProfitRepository extends CrudRepository<Profit,Long> {
}
