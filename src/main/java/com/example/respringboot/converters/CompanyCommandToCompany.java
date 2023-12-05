package com.example.respringboot.converters;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.model.Company;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompanyCommandToCompany implements Converter<CompanyCommand, Company> {

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
        return company;
    }

}
