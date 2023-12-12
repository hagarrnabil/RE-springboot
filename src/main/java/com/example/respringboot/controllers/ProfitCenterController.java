package com.example.respringboot.controllers;

import com.example.respringboot.commands.ProfitCenterCommand;
import com.example.respringboot.repositories.ProfitCenterRepository;
import com.example.respringboot.services.ProfitService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class ProfitCenterController {
    ProfitCenterRepository profitCenterRepository;
    private final ProfitService profitService;

    public ProfitCenterController(ProfitCenterRepository profitCenterRepository, ProfitService profitService) {
        this.profitCenterRepository = profitCenterRepository;
        this.profitService = profitService;
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
    ProfitCenterCommand updateProfitCommand(@RequestBody ProfitCenterCommand newProfitCommand, @PathVariable Long profitCode) {

        profitService.findProfitCommandById(profitCode);
        ProfitCenterCommand savedCommand = profitService.saveProfitCommand(newProfitCommand);
        return savedCommand;
    }
}
