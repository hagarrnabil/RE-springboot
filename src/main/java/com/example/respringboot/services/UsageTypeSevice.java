package com.example.respringboot.services;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.commands.UsageTypeCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UsageType;

import java.util.Set;

public interface UsageTypeSevice {
    Set<UsageTypeCommand> getUsageTypeCommands();

    UsageType findById(Long l);

    void deleteById(Long idToDelete);

    UsageTypeCommand saveUsageTypeCommand(UsageTypeCommand command);

    UsageTypeCommand findUsageTypeCommandById(Long l);
}
