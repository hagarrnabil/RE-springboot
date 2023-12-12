package com.example.respringboot.controllers;

import com.example.respringboot.commands.BuildingAreaCommand;
import com.example.respringboot.services.BuildingAreaService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class BuildingAreaController {
    private final BuildingAreaService buildingAreaService;

    public BuildingAreaController(BuildingAreaService buildingAreaService) {
        this.buildingAreaService = buildingAreaService;
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
    BuildingAreaCommand updateBuildingAreaCommand(@RequestBody BuildingAreaCommand newBuildingAreaCommand, @PathVariable Long buildingAreaCode) {

        buildingAreaService.findBuildingAreaCommandById(buildingAreaCode);
        BuildingAreaCommand savedCommand = buildingAreaService.saveBuildingAreaCommand(newBuildingAreaCommand);
        return savedCommand;
    }
}