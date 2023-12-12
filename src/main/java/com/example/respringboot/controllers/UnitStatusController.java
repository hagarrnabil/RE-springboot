package com.example.respringboot.controllers;

import com.example.respringboot.commands.UnitStatusCommand;
import com.example.respringboot.services.UnitStatusService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class UnitStatusController {
    private final UnitStatusService unitStatusService;

    public UnitStatusController(UnitStatusService unitStatusService) {
        this.unitStatusService = unitStatusService;
    }

    @GetMapping("/unitstatuses")
    Set<UnitStatusCommand> all() {
        return unitStatusService.getUnitStatusCommands();
    }

    @GetMapping("/unitstatuses/{unitStatusCode}")
    public Optional<UnitStatusCommand> findByIds(@PathVariable @NotNull Long unitStatusCode) {

        return Optional.ofNullable(unitStatusService.findUnitStatusCommandById(unitStatusCode));
    }

    @PostMapping("/unitstatuses")
    UnitStatusCommand newUnitStatusCommand(@RequestBody UnitStatusCommand newUnitStatusCommand) {

        UnitStatusCommand savedCommand = unitStatusService.saveUnitStatusCommand(newUnitStatusCommand);
        return savedCommand;

    }

    @DeleteMapping("/unitstatuses/{unitStatusCode}")
    void deleteUnitStatusCommand(@PathVariable Long unitStatusCode) {
        unitStatusService.deleteById(unitStatusCode);
    }

    @PutMapping
    @RequestMapping("/unitstatuses/{unitStatusCode}")
    UnitStatusCommand updateUnitStatusCommand(@RequestBody UnitStatusCommand newUnitStatusCommand, @PathVariable Long unitStatusCode) {

        unitStatusService.findUnitStatusCommandById(unitStatusCode);
        UnitStatusCommand savedCommand = unitStatusService.saveUnitStatusCommand(newUnitStatusCommand);
        return savedCommand;
    }

}
