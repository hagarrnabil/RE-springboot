package com.example.respringboot.services;


import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.model.Company;

import java.util.Set;

public interface CompanyService {
    Set<Company> getCompanies();

    Company findById(Long l);

    void deleteById(Long idToDelete);

    CompanyCommand saveCompanyCommand(CompanyCommand command);

    CompanyCommand findCompanyCommandById(Long l);

}
