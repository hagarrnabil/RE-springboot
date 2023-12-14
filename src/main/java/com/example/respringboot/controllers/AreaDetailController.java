package com.example.respringboot.controllers;

import com.example.respringboot.commands.AreaMasterDetailCommand;
import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.converters.AreaMasterDetailToAreaMasterDetailCommand;
import com.example.respringboot.model.AreaMasterDetail;
import com.example.respringboot.model.Company;
import com.example.respringboot.services.AreaMasterDetailService;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class AreaDetailController {
    private final AreaMasterDetailService areaMasterDetailService;
    private final AreaMasterDetailToAreaMasterDetailCommand areaMasterDetailToAreaMasterDetailCommand;

    public AreaDetailController(AreaMasterDetailService areaMasterDetailService,
                                AreaMasterDetailToAreaMasterDetailCommand areaMasterDetailToAreaMasterDetailCommand) {
        this.areaMasterDetailService = areaMasterDetailService;
        this.areaMasterDetailToAreaMasterDetailCommand = areaMasterDetailToAreaMasterDetailCommand;
    }

    @GetMapping("/areas")
    Set<AreaMasterDetailCommand> all() {
        return areaMasterDetailService.getAreaCommands();
    }

    @GetMapping("/areas/{areaCode}")
    public Optional<AreaMasterDetailCommand> findByIds(@PathVariable @NotNull Long areaCode) {

        return Optional.ofNullable(areaMasterDetailService.findAreaCommandById(areaCode));
    }

    @PostMapping("/areas")
    AreaMasterDetailCommand newAreaDetailCommand(@RequestBody AreaMasterDetailCommand newAreaDetailCommand) {

        AreaMasterDetailCommand savedCommand = areaMasterDetailService.saveAreaCommand(newAreaDetailCommand);
        return savedCommand;

    }

    @DeleteMapping("/areas/{areaCode}")
    void deleteAreaDetailCommand(@PathVariable Long areaCode) {
        areaMasterDetailService.deleteById(areaCode);
    }

    @PutMapping
    @RequestMapping("/areas/{areaCode}")
    @Transactional
    AreaMasterDetailCommand updateAreaDetailCommand(@RequestBody AreaMasterDetail newAreaDetail, @PathVariable Long areaCode) {

        AreaMasterDetail area = areaMasterDetailService.updateArea(newAreaDetail, areaCode);
        AreaMasterDetailCommand command = areaMasterDetailToAreaMasterDetailCommand.convert(area);
        return command;
    }
}
