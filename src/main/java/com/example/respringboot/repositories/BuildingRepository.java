package com.example.respringboot.repositories;

import com.example.respringboot.model.Building;
import org.springframework.data.repository.CrudRepository;

public interface BuildingRepository extends CrudRepository<Building,Long> {
}
