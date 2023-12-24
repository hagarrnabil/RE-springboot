package com.example.respringboot.controllers;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.commands.UnitFloorCommand;
import com.example.respringboot.converters.UnitFloorToUnitFloorCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UnitFloor;
import com.example.respringboot.repositories.UnitFixtureRepository;
import com.example.respringboot.repositories.UnitFloorRepository;
import com.example.respringboot.services.UnitFloorService;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class UnitFloorController {
    private final UnitFloorRepository unitFloorRepository;
    private final UnitFloorService unitFloorService;
    private final UnitFloorToUnitFloorCommand unitFloorToUnitFloorCommand;

    public UnitFloorController(UnitFloorRepository unitFloorRepository, UnitFloorService unitFloorService,
                               UnitFloorToUnitFloorCommand unitFloorToUnitFloorCommand) {
        this.unitFloorRepository = unitFloorRepository;
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
    @Transactional
    UnitFloorCommand newUnitFloorCommand(@RequestBody UnitFloor newUnitFloor) {

        UnitFloor unitFloor = unitFloorRepository.save(newUnitFloor);
        UnitFloorCommand command = unitFloorService.saveUnitFloorCommand(unitFloorToUnitFloorCommand.convert(unitFloor));
        return command;

    }

    @DeleteMapping("/unitfloors/{unitFloorCode}")
    void deleteUnitFloorCommand(@PathVariable Long unitFloorCode) {
        unitFloorService.deleteById(unitFloorCode);
    }

    @PutMapping
    @RequestMapping("/unitfloors/{unitFloorCode}")
    @Transactional
    UnitFloorCommand updateUnitFloorCommand(@RequestBody UnitFloorCommand newUnitFloorCommand, @PathVariable Long unitFloorCode) {

        UnitFloorCommand command = unitFloorToUnitFloorCommand.convert(unitFloorService.updateUnitFloor(newUnitFloorCommand, unitFloorCode));
        return command;
    }

}
