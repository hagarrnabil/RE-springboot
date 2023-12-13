package com.example.respringboot.controllers;

import com.example.respringboot.commands.UsageTypeCommand;
import com.example.respringboot.repositories.UsageTypeRepository;
import com.example.respringboot.services.UsageTypeSevice;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class UsageTypeController {
    UsageTypeRepository usageTypeRepository;
    private final UsageTypeSevice usageTypeSevice;

    public UsageTypeController(UsageTypeRepository usageTypeRepository, UsageTypeSevice usageTypeSevice) {
        this.usageTypeRepository = usageTypeRepository;
        this.usageTypeSevice = usageTypeSevice;
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
    UsageTypeCommand updateUsageTypeCommand(@RequestBody UsageTypeCommand newUsageTypeCommand, @PathVariable Long usageTypeCode) {

        usageTypeSevice.findUsageTypeCommandById(usageTypeCode);
        UsageTypeCommand savedCommand = usageTypeSevice.saveUsageTypeCommand(newUsageTypeCommand);
        return savedCommand;
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/usagetype/search")
//    @ResponseBody
//    public List<CompanyCommand> Search(@RequestParam String keyword) {
//
//        return companyRepository.search(keyword);
//    }
}
