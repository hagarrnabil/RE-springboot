package com.example.respringboot.controllers;

import com.example.respringboot.commands.UnitFixtureCommand;
import com.example.respringboot.commands.UsageTypeCommand;
import com.example.respringboot.converters.UnitFixtureToUnitFixtureCommand;
import com.example.respringboot.model.UnitFixture;
import com.example.respringboot.repositories.UnitFixtureRepository;
import com.example.respringboot.services.UnitFixtureService;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class UnitFixtureController {
    private final UnitFixtureRepository unitFixtureRepository;
    private final UnitFixtureService unitFixtureService;
    private final UnitFixtureToUnitFixtureCommand unitFixtureToUnitFixtureCommand;

    public UnitFixtureController(UnitFixtureRepository unitFixtureRepository, UnitFixtureService unitFixtureService,
                                 UnitFixtureToUnitFixtureCommand unitFixtureToUnitFixtureCommand) {
        this.unitFixtureRepository = unitFixtureRepository;
        this.unitFixtureService = unitFixtureService;
        this.unitFixtureToUnitFixtureCommand = unitFixtureToUnitFixtureCommand;
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
    UnitFixtureCommand newUnitFixtureCommand(@RequestBody UnitFixture unitFixture) {

        UnitFixture unitFixture1 = unitFixtureRepository.save(unitFixture);
        UnitFixtureCommand command = unitFixtureService.saveUnitFixtureCommand(unitFixtureToUnitFixtureCommand.convert(unitFixture1));
        return command;
    }

//    @PostMapping("/unitfixture")
//    UnitFixtureCommand newUnitFixtureCommand(@RequestBody UnitFixtureCommand newUnitFixtureCommand) {
//
//        UnitFixtureCommand savedCommand = unitFixtureService.saveUnitFixtureCommand(newUnitFixtureCommand);
//        return savedCommand;
//
//    }

    @DeleteMapping("/unitfixture/{unitFixtureCode}")
    void deleteUnitFixtureCommand(@PathVariable Long unitFixtureCode) {
        unitFixtureService.deleteById(unitFixtureCode);
    }

    @PutMapping
    @RequestMapping("/unitfixture/{unitFixtureCode}")
    @Transactional
    UnitFixtureCommand updateUnitFixtureCommand(@RequestBody UnitFixture newUnitFixture, @PathVariable Long unitFixtureCode) {

        UnitFixture unitFixture = unitFixtureService.updateUnitFixture(newUnitFixture, unitFixtureCode);
        UnitFixtureCommand command = unitFixtureToUnitFixtureCommand.convert(unitFixture);
        return command;
    }

}
