package com.example.respringboot.controllers;

import com.example.respringboot.commands.UnitViewCommand;
import com.example.respringboot.repositories.UnitViewRepository;
import com.example.respringboot.services.UnitViewService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class UnitViewController {
    private final UnitViewService unitViewService;

    public UnitViewController(UnitViewService unitViewService) {
        this.unitViewService = unitViewService;
    }

    @GetMapping("/unitviews")
    Set<UnitViewCommand> all() {
        return unitViewService.getUnitViewCommands();
    }

    @GetMapping("/unitviews/{unitViewCode}")
    public Optional<UnitViewCommand> findByIds(@PathVariable @NotNull Long unitViewCode) {

        return Optional.ofNullable(unitViewService.findUnitViewCommandById(unitViewCode));
    }

    @PostMapping("/unitviews")
    UnitViewCommand newUnitViewCommand(@RequestBody UnitViewCommand newUnitViewCommand) {

        UnitViewCommand savedCommand = unitViewService.saveUnitViewCommand(newUnitViewCommand);
        return savedCommand;

    }

    @DeleteMapping("/unitviews/{unitViewCode}")
    void deleteUnitViewCommand(@PathVariable Long unitViewCode) {
        unitViewService.deleteById(unitViewCode);
    }

    @PutMapping
    @RequestMapping("/unitviews/{unitViewCode}")
    UnitViewCommand updateUnitViewCommand(@RequestBody UnitViewCommand newUnitViewCommand, @PathVariable Long unitViewCode) {

        unitViewService.findUnitViewCommandById(unitViewCode);
        UnitViewCommand savedCommand = unitViewService.saveUnitViewCommand(newUnitViewCommand);
        return savedCommand;
    }
}
