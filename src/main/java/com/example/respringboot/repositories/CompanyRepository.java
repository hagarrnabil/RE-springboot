package com.example.respringboot.repositories;

import com.example.respringboot.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company,Long> {
}
