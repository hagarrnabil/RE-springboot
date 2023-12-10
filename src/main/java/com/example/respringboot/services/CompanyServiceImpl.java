package com.example.respringboot.services;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.converters.CompanyCommandToCompany;
import com.example.respringboot.converters.CompanyToCompanyCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.repositories.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyCommandToCompany companyCommandToCompany;
    private final CompanyToCompanyCommand companyToCompanyCommand;
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyCommandToCompany companyCommandToCompany, CompanyToCompanyCommand companyToCompanyCommand,
                              CompanyRepository companyRepository) {
        this.companyCommandToCompany = companyCommandToCompany;
        this.companyToCompanyCommand = companyToCompanyCommand;
        this.companyRepository = companyRepository;
    }

    @Override
    public Set<CompanyCommand> getCompanies() {
        log.debug("I'm in the service");
        Set<Company> companies = new HashSet<>();
        companyRepository.findAll().iterator().forEachRemaining(companies::add);
        CompanyCommand command = new CompanyCommand();
        Object[] companyCommandsArrays = command.toArray();
        for (int i = 0; i < companyCommandsArrays.length; i++) {
            companyToCompanyCommand.convert((Company) getCompanies());
        }
        return (Set<CompanyCommand>) command;
    }

    @Override
    public Company findById(Long l) {
        Optional<Company> companyOptional = companyRepository.findById(l);

        if (!companyOptional.isPresent()) {
            throw new RuntimeException("Company Not Found!");
        }

        return companyOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        companyRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public CompanyCommand saveCompanyCommand(CompanyCommand command) {

        Company detachedCompany = companyCommandToCompany.convert(command);
        Company savedCompany = companyRepository.save(detachedCompany);
        log.debug("Saved Company Id:" + savedCompany.getId());
        return companyToCompanyCommand.convert(savedCompany);
    }

    @Override
    public CompanyCommand findCompanyCommandById(Long l) {
        return companyToCompanyCommand.convert(findById(l));
    }

//    @Override
//    public CompanyCommand findAllCompanyCommand() {
//
//    }
}
