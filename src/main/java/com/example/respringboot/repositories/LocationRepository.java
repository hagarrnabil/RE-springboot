package com.example.respringboot.repositories;

import com.example.respringboot.model.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location,Long> {
}
