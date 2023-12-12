package com.example.respringboot.controllers;

import com.example.respringboot.commands.UnitSubtypeCommand;
import com.example.respringboot.services.UnitSubtypeService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class USubtypeController {
    private final UnitSubtypeService unitSubtypeService;

    public USubtypeController(UnitSubtypeService unitSubtypeService) {
        this.unitSubtypeService = unitSubtypeService;
    }

    @GetMapping("/unitsubtypes")
    Set<UnitSubtypeCommand> all() {
        return unitSubtypeService.getUnitSubtypeCommands();
    }

    @GetMapping("/unitsubtypes/{unitSubtypeCode}")
    public Optional<UnitSubtypeCommand> findByIds(@PathVariable @NotNull Long unitSubtypeCode) {

        return Optional.ofNullable(unitSubtypeService.findUnitSubtypeCommandById(unitSubtypeCode));
    }

    @PostMapping("/unitsubtypes")
    UnitSubtypeCommand newUnitSubtypeCommand(@RequestBody UnitSubtypeCommand newUnitSubtypeCommand) {

        UnitSubtypeCommand savedCommand = unitSubtypeService.saveUnitSubtypeCommand(newUnitSubtypeCommand);
        return savedCommand;

    }

    @DeleteMapping("/unitsubtypes/{unitSubtypeCode}")
    void deleteUnitSubtypeCommand(@PathVariable Long unitSubtypeCode) {
        unitSubtypeService.deleteById(unitSubtypeCode);
    }

    @PutMapping
    @RequestMapping("/unitsubtypes/{unitSubtypeCode}")
    UnitSubtypeCommand updateUnitSubtypeCommand(@RequestBody UnitSubtypeCommand newUnitSubtypeCommand, @PathVariable Long unitSubtypeCode) {

        unitSubtypeService.findUnitSubtypeCommandById(unitSubtypeCode);
        UnitSubtypeCommand savedCommand = unitSubtypeService.saveUnitSubtypeCommand(newUnitSubtypeCommand);
        return savedCommand;
    }

}
