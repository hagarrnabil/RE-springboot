package com.example.respringboot.controllers;

import com.example.respringboot.commands.UnitFixtureCommand;
import com.example.respringboot.services.UnitFixtureService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class UnitFixtureController {
    private final UnitFixtureService unitFixtureService;

    public UnitFixtureController(UnitFixtureService unitFixtureService) {
        this.unitFixtureService = unitFixtureService;
    }

    @GetMapping("/unitfixtures")
    Set<UnitFixtureCommand> all() {
        return unitFixtureService.getUnitFixtureCommands();
    }

    @GetMapping("/unitfixtures/{unitFixtureCode}")
    public Optional<UnitFixtureCommand> findByIds(@PathVariable @NotNull Long unitFixtureCode) {

        return Optional.ofNullable(unitFixtureService.findUnitFixtureCommandById(unitFixtureCode));
    }

    @PostMapping("/unitfixtures")
    UnitFixtureCommand newUnitFixtureCommand(@RequestBody UnitFixtureCommand newUnitFixtureCommand) {

        UnitFixtureCommand savedCommand = unitFixtureService.saveUnitFixtureCommand(newUnitFixtureCommand);
        return savedCommand;

    }

    @DeleteMapping("/unitfixtures/{unitFixtureCode}")
    void deleteUnitFixtureCommand(@PathVariable Long unitFixtureCode) {
        unitFixtureService.deleteById(unitFixtureCode);
    }

    @PutMapping
    @RequestMapping("/unitfixtures/{unitFixtureCode}")
    UnitFixtureCommand updateUnitFixtureCommand(@RequestBody UnitFixtureCommand newUnitFixtureCommand, @PathVariable Long unitFixtureCode) {

        unitFixtureService.findUnitFixtureCommandById(unitFixtureCode);
        UnitFixtureCommand savedCommand = unitFixtureService.saveUnitFixtureCommand(newUnitFixtureCommand);
        return savedCommand;
    }

}
