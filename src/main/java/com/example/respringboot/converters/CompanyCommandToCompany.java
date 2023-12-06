package com.example.respringboot.converters;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.model.Company;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompanyCommandToCompany implements Converter<CompanyCommand, Company> {
    private final CompanyCommandToCompany companyConverter;

    private final ProjectCommandToProject projectConverter;

    public CompanyCommandToCompany(CompanyCommandToCompany companyConverter, ProjectCommandToProject projectConverter) {
        this.companyConverter = companyConverter;
        this.projectConverter = projectConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Company convert(CompanyCommand source) {
        if (source == null) {
            return null;
        }

        final Company company = new Company();
        company.setId(source.getId());
        company.setCompanyCodeId(source.getCompanyCodeId());
        company.setCompanyCodeDescription(source.getCompanyCodeDescription());
        if (source.getProjectCommands() != null && source.getProjectCommands().size() > 0){
            source.getProjectCommands()
                    .forEach( projectCommand -> company.getProjects().add(companyConverter.convert(projectCommand)));
        }

        return company;
    }

}
