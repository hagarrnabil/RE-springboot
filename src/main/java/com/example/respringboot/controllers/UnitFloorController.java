package com.example.respringboot.controllers;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.commands.UnitFloorCommand;
import com.example.respringboot.converters.UnitFloorToUnitFloorCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UnitFloor;
import com.example.respringboot.services.UnitFloorService;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class UnitFloorController {
    private final UnitFloorService unitFloorService;
    private final UnitFloorToUnitFloorCommand unitFloorToUnitFloorCommand;

    public UnitFloorController(UnitFloorService unitFloorService,
                               UnitFloorToUnitFloorCommand unitFloorToUnitFloorCommand) {
        this.unitFloorService = unitFloorService;
        this.unitFloorToUnitFloorCommand = unitFloorToUnitFloorCommand;
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
    @Transactional
    UnitFloorCommand updateUnitFloorCommand(@RequestBody UnitFloor newUnitFloor, @PathVariable Long unitFloorCode) {

        UnitFloor unitFloor = unitFloorService.updateUnitFloor(newUnitFloor, unitFloorCode);
        UnitFloorCommand command = unitFloorToUnitFloorCommand.convert(unitFloor);
        return command;
    }

}
