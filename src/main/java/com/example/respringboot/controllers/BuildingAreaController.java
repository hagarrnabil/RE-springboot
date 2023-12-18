package com.example.respringboot.controllers;

import com.example.respringboot.commands.BuildingAreaCommand;
import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.converters.BuildingAreaToBuildingAreaCommand;
import com.example.respringboot.model.BuildingArea;
import com.example.respringboot.model.Company;
import com.example.respringboot.services.BuildingAreaService;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class BuildingAreaController {
    private final BuildingAreaService buildingAreaService;
    private final BuildingAreaToBuildingAreaCommand buildingAreaToBuildingAreaCommand;

    public BuildingAreaController(BuildingAreaService buildingAreaService, BuildingAreaToBuildingAreaCommand buildingAreaToBuildingAreaCommand) {
        this.buildingAreaService = buildingAreaService;
        this.buildingAreaToBuildingAreaCommand = buildingAreaToBuildingAreaCommand;
    }

    @GetMapping("/buildingareas")
    Set<BuildingAreaCommand> all() {
        return buildingAreaService.getBuildingAreaCommands();
    }

    @GetMapping("/buildingareas/{buildingAreaCode}")
    public Optional<BuildingAreaCommand> findByIds(@PathVariable @NotNull Long buildingAreaCode) {

        return Optional.ofNullable(buildingAreaService.findBuildingAreaCommandById(buildingAreaCode));
    }

    @PostMapping("/buildingareas")
    BuildingAreaCommand newBuildingAreaCommand(@RequestBody BuildingAreaCommand newBuildingAreaCommand) {

        BuildingAreaCommand savedCommand = buildingAreaService.saveBuildingAreaCommand(newBuildingAreaCommand);
        return savedCommand;

    }

    @DeleteMapping("/buildingareas/{buildingAreaCode}")
    void deleteBuildingAreaCommand(@PathVariable Long buildingAreaCode) {
        buildingAreaService.deleteById(buildingAreaCode);
    }

    @PutMapping
    @RequestMapping("/buildingareas/{buildingAreaCode}")
    @Transactional
    BuildingAreaCommand updateBuildingAreaCommand(@RequestBody BuildingArea newBuildingArea, @PathVariable Long buildingAreaCode) {

        BuildingAreaCommand command = buildingAreaToBuildingAreaCommand.convert(buildingAreaService.updateBuildingArea(newBuildingArea, buildingAreaCode));
        return command;
    }
}