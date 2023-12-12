package com.example.respringboot.controllers;

import com.example.respringboot.commands.UnitOrientationCommand;
import com.example.respringboot.services.UnitOrientationService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class UnitOrientationController {
    private final UnitOrientationService unitOrientationService;

    public UnitOrientationController(UnitOrientationService unitOrientationService) {
        this.unitOrientationService = unitOrientationService;
    }

    @GetMapping("/unitorientations")
    Set<UnitOrientationCommand> all() {
        return unitOrientationService.getUnitOrientationCommands();
    }

    @GetMapping("/unitorientations/{unitOrientationCode}")
    public Optional<UnitOrientationCommand> findByIds(@PathVariable @NotNull Long unitOrientationCode) {

        return Optional.ofNullable(unitOrientationService.findUnitOrientationCommandById(unitOrientationCode));
    }

    @PostMapping("/unitorientations")
    UnitOrientationCommand newUnitOrientationCommand(@RequestBody UnitOrientationCommand newUnitOrientationCommand) {

        UnitOrientationCommand savedCommand = unitOrientationService.saveUnitOrientationCommand(newUnitOrientationCommand);
        return savedCommand;

    }

    @DeleteMapping("/unitorientations/{unitOrientationCode}")
    void deleteUnitOrientationCommand(@PathVariable Long companyCode) {
        unitOrientationService.deleteById(companyCode);
    }

    @PutMapping
    @RequestMapping("/unitorientations/{unitOrientationCode}")
    UnitOrientationCommand updateUnitOrientationCommand(@RequestBody UnitOrientationCommand newUnitOrientationCommand, @PathVariable Long unitOrientationCode) {

        unitOrientationService.findUnitOrientationCommandById(unitOrientationCode);
        UnitOrientationCommand savedCommand = unitOrientationService.saveUnitOrientationCommand(newUnitOrientationCommand);
        return savedCommand;
    }

}
