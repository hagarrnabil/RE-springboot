package com.example.respringboot.controllers;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.commands.UnitStatusCommand;
import com.example.respringboot.converters.UnitStatusToUnitStatusCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UnitStatus;
import com.example.respringboot.repositories.UnitStatusRepository;
import com.example.respringboot.services.UnitStatusService;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class UnitStatusController {
    private final UnitStatusRepository unitStatusRepository;
    private final UnitStatusService unitStatusService;
    private final UnitStatusToUnitStatusCommand unitStatusToUnitStatusCommand;

    public UnitStatusController(UnitStatusRepository unitStatusRepository, UnitStatusService unitStatusService,
                                UnitStatusToUnitStatusCommand unitStatusToUnitStatusCommand) {
        this.unitStatusRepository = unitStatusRepository;
        this.unitStatusService = unitStatusService;
        this.unitStatusToUnitStatusCommand = unitStatusToUnitStatusCommand;
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
    @Transactional
    UnitStatusCommand newUnitStatusCommand(@RequestBody UnitStatus newUnitStatus) {

        UnitStatus unitStatus = unitStatusRepository.save(newUnitStatus);
        UnitStatusCommand command = unitStatusService.saveUnitStatusCommand(unitStatusToUnitStatusCommand.convert(unitStatus));
        return command;

    }

    @DeleteMapping("/unitstatuses/{unitStatusCode}")
    void deleteUnitStatusCommand(@PathVariable Long unitStatusCode) {
        unitStatusService.deleteById(unitStatusCode);
    }

    @PutMapping
    @RequestMapping("/unitstatuses/{unitStatusCode}")
    @Transactional
    UnitStatusCommand updateUnitStatusCommand(@RequestBody UnitStatus newUnitStatus, @PathVariable Long unitStatusCode) {

        UnitStatusCommand command = unitStatusToUnitStatusCommand.convert(unitStatusService.updateUnitStatus(newUnitStatus, unitStatusCode));
        return command;
    }

}
