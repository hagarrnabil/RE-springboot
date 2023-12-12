package com.example.respringboot.controllers;

import com.example.respringboot.commands.AreaMasterDetailCommand;
import com.example.respringboot.services.AreaMasterDetailService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class AreaDetailController {
    private final AreaMasterDetailService areaMasterDetailService;

    public AreaDetailController(AreaMasterDetailService areaMasterDetailService) {
        this.areaMasterDetailService = areaMasterDetailService;
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
    AreaMasterDetailCommand updateAreaDetailCommand(@RequestBody AreaMasterDetailCommand newAreaDetailCommand, @PathVariable Long areaCode) {

        areaMasterDetailService.findAreaCommandById(areaCode);
        AreaMasterDetailCommand savedCommand = areaMasterDetailService.saveAreaCommand(newAreaDetailCommand);
        return savedCommand;
    }
}
