package com.example.respringboot.repositories;

import com.example.respringboot.model.UnitPaymentDetails;
import org.springframework.data.repository.CrudRepository;

public interface UPDRepository extends CrudRepository<UnitPaymentDetails,Long> {
}
