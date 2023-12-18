package com.example.respringboot.controllers;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.commands.UsageTypeCommand;
import com.example.respringboot.converters.UsageTypeToUsageTypeCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UsageType;
import com.example.respringboot.repositories.UsageTypeRepository;
import com.example.respringboot.services.UsageTypeSevice;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class UsageTypeController {
    private final UsageTypeRepository usageTypeRepository;
    private final UsageTypeSevice usageTypeSevice;
    private final UsageTypeToUsageTypeCommand usageTypeToUsageTypeCommand;

    public UsageTypeController(UsageTypeRepository usageTypeRepository, UsageTypeSevice usageTypeSevice,
                               UsageTypeToUsageTypeCommand usageTypeToUsageTypeCommand) {
        this.usageTypeRepository = usageTypeRepository;
        this.usageTypeSevice = usageTypeSevice;
        this.usageTypeToUsageTypeCommand = usageTypeToUsageTypeCommand;
    }

    @GetMapping("/usagetype")
    Set<UsageTypeCommand> all() {
        return usageTypeSevice.getUsageTypeCommands();
    }

    @GetMapping("/usagetype/{usageTypeCode}")
    public Optional<UsageTypeCommand> findByIds(@PathVariable @NotNull Long usageTypeCode) {

        return Optional.ofNullable(usageTypeSevice.findUsageTypeCommandById(usageTypeCode));
    }

    @PostMapping("/usagetype")
    UsageTypeCommand newUsageTypeCommand(@RequestBody UsageTypeCommand newUsageTypeCommand) {

        UsageTypeCommand savedCommand = usageTypeSevice.saveUsageTypeCommand(newUsageTypeCommand);
        return savedCommand;

    }

    @DeleteMapping("/usagetype/{usageTypeCode}")
    void deleteUsageTypeCommand(@PathVariable Long usageTypeCode) {
        usageTypeSevice.deleteById(usageTypeCode);
    }

    @PutMapping
    @RequestMapping("/usagetype/{usageTypeCode}")
    @Transactional
    UsageTypeCommand updateUsageTypeCommand(@RequestBody UsageType newUsageType, @PathVariable Long usageTypeCode) {

        UsageTypeCommand command = usageTypeToUsageTypeCommand.convert(usageTypeSevice.updateUsageType(newUsageType, usageTypeCode));
        return command;
    }
}
