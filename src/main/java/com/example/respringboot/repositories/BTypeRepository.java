package com.example.respringboot.repositories;

import com.example.respringboot.model.BuildingType;
import org.springframework.data.repository.CrudRepository;

public interface BTypeRepository extends CrudRepository<BuildingType,Long> {
}
