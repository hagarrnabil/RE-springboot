package com.example.respringboot.controllers;

import com.example.respringboot.commands.UnitFixtureCommand;
import com.example.respringboot.repositories.UnitFixtureRepository;
import com.example.respringboot.services.UnitFixtureService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class UnitFixtureController {
    UnitFixtureRepository unitFixtureRepository;
    private final UnitFixtureService unitFixtureService;

    public UnitFixtureController(UnitFixtureRepository unitFixtureRepository, UnitFixtureService unitFixtureService) {
        this.unitFixtureRepository = unitFixtureRepository;
        this.unitFixtureService = unitFixtureService;
    }

    @GetMapping("/unitfixture")
    Set<UnitFixtureCommand> all() {
        return unitFixtureService.getUnitFixtureCommands();
    }

    @GetMapping("/unitfixture/{unitFixtureCode}")
    public Optional<UnitFixtureCommand> findByIds(@PathVariable @NotNull Long unitFixtureCode) {

        return Optional.ofNullable(unitFixtureService.findUnitFixtureCommandById(unitFixtureCode));
    }

    @PostMapping("/unitfixture")
    UnitFixtureCommand newUnitFixtureCommand(@RequestBody UnitFixtureCommand newUnitFixtureCommand) {

        UnitFixtureCommand savedCommand = unitFixtureService.saveUnitFixtureCommand(newUnitFixtureCommand);
        return savedCommand;
    }

    @DeleteMapping("/unitfixture/{unitFixtureCode}")
    void deleteUnitFixtureCommand(@PathVariable Long unitFixtureCode) {
        unitFixtureService.deleteById(unitFixtureCode);
    }

    @PutMapping
    @RequestMapping("/unitfixture/{unitFixtureCode}")
    UnitFixtureCommand updateUnitFixtureCommand(@RequestBody UnitFixtureCommand newUnitFixtureCommand, @PathVariable Long unitFixtureCode) {

        unitFixtureService.findUnitFixtureCommandById(unitFixtureCode);
        UnitFixtureCommand savedCommand = unitFixtureService.saveUnitFixtureCommand(newUnitFixtureCommand);
        return savedCommand;
    }

}
