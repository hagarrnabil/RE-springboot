package com.example.respringboot.controllers;


import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.commands.UnitViewCommand;
import com.example.respringboot.converters.UnitViewToUnitViewCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UnitView;
import com.example.respringboot.repositories.UnitViewRepository;
import com.example.respringboot.services.UnitViewService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class UnitViewController {
    UnitViewRepository unitViewRepository;
    private final UnitViewService unitViewService;
    private final UnitViewToUnitViewCommand unitViewToUnitViewCommand;

    public UnitViewController(UnitViewRepository unitViewRepository, UnitViewService unitViewService,
                              UnitViewToUnitViewCommand unitViewToUnitViewCommand) {
        this.unitViewRepository = unitViewRepository;
        this.unitViewService = unitViewService;
        this.unitViewToUnitViewCommand = unitViewToUnitViewCommand;
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
    UnitViewCommand newUnitViewCommand(@Valid  @RequestBody UnitViewCommand newUnitViewCommand) {

        UnitViewCommand savedCommand = unitViewService.saveUnitViewCommand(newUnitViewCommand);
        return savedCommand;

    }

    @DeleteMapping("/unitviews/{unitViewCode}")
    void deleteUnitViewCommand(@PathVariable Long unitViewCode) {
        unitViewService.deleteById(unitViewCode);
    }

    @PutMapping
    @RequestMapping("/unitviews/{unitViewCode}")
    @Transactional
    UnitViewCommand updateUnitViewCommand(@RequestBody UnitView newUnitView, @PathVariable Long unitViewCode) {

        UnitView unitView = unitViewService.updateUnitView(newUnitView, unitViewCode);
        UnitViewCommand command = unitViewToUnitViewCommand.convert(unitView);
        return command;
    }
}
