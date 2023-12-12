package com.example.respringboot.controllers;

import com.example.respringboot.commands.UnitFloorCommand;
import com.example.respringboot.services.UnitFloorService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class UnitFloorController {
    private final UnitFloorService unitFloorService;

    public UnitFloorController(UnitFloorService unitFloorService) {
        this.unitFloorService = unitFloorService;
    }

    @GetMapping("/unitfloors")
    Set<UnitFloorCommand> all() {
        return unitFloorService.getUnitFloorCommands();
    }

    @GetMapping("/unitfloors/{unitFloorCode}")
    public Optional<UnitFloorCommand> findByIds(@PathVariable @NotNull Long unitFloorCode) {

        return Optional.ofNullable(unitFloorService.findUnitFloorCommandById(unitFloorCode));
    }

    @PostMapping("/unitfloors")
    UnitFloorCommand newUnitFloorCommand(@RequestBody UnitFloorCommand newUnitFloorCommand) {

        UnitFloorCommand savedCommand = unitFloorService.saveUnitFloorCommand(newUnitFloorCommand);
        return savedCommand;

    }

    @DeleteMapping("/unitfloors/{unitFloorCode}")
    void deleteUnitFloorCommand(@PathVariable Long unitFloorCode) {
        unitFloorService.deleteById(unitFloorCode);
    }

    @PutMapping
    @RequestMapping("/unitfloors/{unitFloorCode}")
    UnitFloorCommand updateUnitFloorCommand(@RequestBody UnitFloorCommand newUnitFloorCommand, @PathVariable Long unitFloorCode) {

        unitFloorService.findUnitFloorCommandById(unitFloorCode);
        UnitFloorCommand savedCommand = unitFloorService.saveUnitFloorCommand(newUnitFloorCommand);
        return savedCommand;
    }

}
