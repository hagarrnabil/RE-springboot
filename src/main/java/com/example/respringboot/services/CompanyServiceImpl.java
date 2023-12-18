package com.example.respringboot.services;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.converters.CompanyCommandToCompany;
import com.example.respringboot.converters.CompanyToCompanyCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.repositories.CompanyRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    @Transactional
    public Set<CompanyCommand> getCompanyCommands() {
        return StreamSupport.stream(companyRepository.findAll()
                        .spliterator(), false)
                .map(companyToCompanyCommand::convert)
                .collect(Collectors.toSet());
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
        log.debug("Saved Company Id:" + savedCompany.getCompanyCode());
        return companyToCompanyCommand.convert(savedCompany);

    }

    @Override
    public Company updateCompany(Company company, Long l) {
        return companyRepository.findById(l).map(company1 -> {
            company1.setCompanyCode(company.getCompanyCode());
            company1.setCompanyCodeId(company.getCompanyCodeId());
            company1.setCompanyCodeDescription(company.getCompanyCodeDescription());
            return companyRepository.save(company);
        }).orElseGet(() -> {
            company.setCompanyCode(l);
            return companyRepository.save(company);
        });
    }


    @Override
    @Transactional
    public CompanyCommand findCompanyCommandById(Long l) {
        return companyToCompanyCommand.convert(findById(l));
    }

}
