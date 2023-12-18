package com.example.respringboot.controllers;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.commands.ProfitCenterCommand;
import com.example.respringboot.converters.ProfitToProfitCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.ProfitCenter;
import com.example.respringboot.repositories.ProfitCenterRepository;
import com.example.respringboot.services.ProfitService;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class ProfitCenterController {
    private final ProfitCenterRepository profitCenterRepository;
    private final ProfitService profitService;
    private final ProfitToProfitCommand profitToProfitCommand;

    public ProfitCenterController(ProfitCenterRepository profitCenterRepository, ProfitService profitService,
                                  ProfitToProfitCommand profitToProfitCommand) {
        this.profitCenterRepository = profitCenterRepository;
        this.profitService = profitService;
        this.profitToProfitCommand = profitToProfitCommand;
    }

    @GetMapping("/profits")
    Set<ProfitCenterCommand> all() {
        return profitService.getProfitCommands();
    }

    @GetMapping("/profits/{profitCode}")
    public Optional<ProfitCenterCommand> findByIds(@PathVariable @NotNull Long profitCode) {

        return Optional.ofNullable(profitService.findProfitCommandById(profitCode));
    }

    @PostMapping("/profits")
    ProfitCenterCommand newProfitCommand(@RequestBody ProfitCenterCommand newProfitCommand) {

        ProfitCenterCommand savedCommand = profitService.saveProfitCommand(newProfitCommand);
        return savedCommand;

    }

    @DeleteMapping("/profits/{profitCode}")
    void deleteProfitCommand(@PathVariable Long profitCode) {
        profitService.deleteById(profitCode);
    }

    @PutMapping
    @RequestMapping("/profits/{profitCode}")
    @Transactional
    ProfitCenterCommand updateProfitCommand(@RequestBody ProfitCenter newProfit, @PathVariable Long profitCode) {

        ProfitCenterCommand command = profitToProfitCommand.convert(profitService.updateProfit(newProfit, profitCode));
        return command;
    }
}
