package com.example.respringboot.services;


import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.model.Company;

import java.util.Set;

public interface CompanyService {
    Set<CompanyCommand> getCompanyCommands();

    Company findById(Long l);

    void deleteById(Long idToDelete);

    CompanyCommand saveCompanyCommand(CompanyCommand command);

    Company updateCompany(CompanyCommand newCompanyCommand, Long l);

    CompanyCommand findCompanyCommandById(Long l);

}
