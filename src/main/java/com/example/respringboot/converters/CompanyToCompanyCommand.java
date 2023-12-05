package com.example.respringboot.converters;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.model.Company;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CompanyToCompanyCommand implements Converter<Company, CompanyCommand> {

    @Synchronized
    @Nullable
    @Override
    public CompanyCommand convert(Company source) {
        if (source == null) {
            return null;
        }

        final CompanyCommand companyCommand = new CompanyCommand();
        companyCommand.setId(source.getId());
        companyCommand.setCompanyCodeId(source.getCompanyCodeId());
        companyCommand.setCompanyCodeDescription(source.getCompanyCodeDescription());
        return companyCommand;
    }

}
