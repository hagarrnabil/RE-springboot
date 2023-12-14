package com.example.respringboot.services;

import com.example.respringboot.commands.UsageTypeCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UsageType;

import java.util.Set;

public interface UsageTypeSevice {
    Set<UsageTypeCommand> getUsageTypeCommands();

    UsageType findById(Long l);

    void deleteById(Long idToDelete);

    UsageTypeCommand saveUsageTypeCommand(UsageTypeCommand command);
    UsageType updateUsageType(UsageType usageType, Long l);

    UsageTypeCommand findUsageTypeCommandById(Long l);
}
