package com.example.respringboot.controllers;
;
import com.example.respringboot.commands.UnitAreaCommand;
import com.example.respringboot.services.UnitAreaService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class UnitAreaController {
    private final UnitAreaService unitAreaService;

    public UnitAreaController(UnitAreaService unitAreaService) {
        this.unitAreaService = unitAreaService;
    }

    @GetMapping("/unitareas")
    Set<UnitAreaCommand> all() {
        return unitAreaService.getUnitAreaCommands();
    }

    @GetMapping("/unitareas/{unitAreaCode}")
    public Optional<UnitAreaCommand> findByIds(@PathVariable @NotNull Long unitAreaCode) {

        return Optional.ofNullable(unitAreaService.findUnitAreaCommandById(unitAreaCode));
    }

    @PostMapping("/unitareas")
    UnitAreaCommand newUnitAreaCommand(@RequestBody UnitAreaCommand newUnitAreaCommand) {

        UnitAreaCommand savedCommand = unitAreaService.saveUnitAreaCommand(newUnitAreaCommand);
        return savedCommand;

    }

    @DeleteMapping("/unitareas/{unitAreaCode}")
    void deleteUnitAreaCommand(@PathVariable Long unitAreaCode) {
        unitAreaService.deleteById(unitAreaCode);
    }

    @PutMapping
    @RequestMapping("/unitareas/{unitAreaCode}")
    UnitAreaCommand updateUnitAreaCommand(@RequestBody UnitAreaCommand newUnitAreaCommand, @PathVariable Long unitAreaCode) {

        unitAreaService.findUnitAreaCommandById(unitAreaCode);
        UnitAreaCommand savedCommand = unitAreaService.saveUnitAreaCommand(newUnitAreaCommand);
        return savedCommand;
    }
}
