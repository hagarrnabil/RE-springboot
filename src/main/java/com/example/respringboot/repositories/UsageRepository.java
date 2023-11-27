package com.example.respringboot.repositories;

import com.example.respringboot.model.UsageType;
import org.springframework.data.repository.CrudRepository;

public interface UsageRepository extends CrudRepository<UsageType,Long> {
}
