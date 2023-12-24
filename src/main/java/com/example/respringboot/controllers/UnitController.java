package com.example.respringboot.controllers;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.commands.UnitCommand;
import com.example.respringboot.converters.UnitToUnitCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.Unit;
import com.example.respringboot.repositories.UnitRepository;
import com.example.respringboot.services.UnitService;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class UnitController {
    private final UnitRepository unitRepository;
    private final UnitService unitService;
    private final UnitToUnitCommand unitToUnitCommand;

    public UnitController(UnitRepository unitRepository, UnitService unitService,
                          UnitToUnitCommand unitToUnitCommand) {
        this.unitRepository = unitRepository;
        this.unitService = unitService;
        this.unitToUnitCommand = unitToUnitCommand;
    }

    @GetMapping("/units")
    Set<UnitCommand> all() {
        return unitService.getUnitCommands();
    }

    @GetMapping("/units/{unitCode}")
    public Optional<UnitCommand> findByIds(@PathVariable @NotNull Long unitCode) {

        return Optional.ofNullable(unitService.findUnitCommandById(unitCode));
    }

    @PostMapping("/units")
    UnitCommand newUnitCommand(@RequestBody UnitCommand newUnitCommand) {

        UnitCommand savedCommand = unitService.saveUnitCommand(newUnitCommand);
        return savedCommand;

    }

    @DeleteMapping("/units/{unitCode}")
    void deleteUnitCommand(@PathVariable Long unitCode) {
        unitService.deleteById(unitCode);
    }

    @PutMapping
    @RequestMapping("/units/{unitCode}")
    @Transactional
    UnitCommand updateUnitCommand(@RequestBody UnitCommand newUnitCommand, @PathVariable Long unitCode) {

        UnitCommand command = unitToUnitCommand.convert(unitService.updateUnit(newUnitCommand, unitCode));
        return command;
    }
}