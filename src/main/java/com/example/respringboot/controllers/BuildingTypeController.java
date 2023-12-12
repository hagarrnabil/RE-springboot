package com.example.respringboot.controllers;

import com.example.respringboot.commands.BuildingTypeCommand;
import com.example.respringboot.repositories.BuildingTypeRepository;
import com.example.respringboot.services.BuildingTypeService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class BuildingTypeController {
    BuildingTypeRepository buildingTypeRepository;
    private final BuildingTypeService buildingTypeService;

    public BuildingTypeController(BuildingTypeRepository buildingTypeRepository, BuildingTypeService buildingTypeService) {
        this.buildingTypeRepository = buildingTypeRepository;
        this.buildingTypeService = buildingTypeService;
    }

    @GetMapping("/buildingtypes")
    Set<BuildingTypeCommand> all() {
        return buildingTypeService.getBuildingTypeCommands();
    }

    @GetMapping("/buildingtypes/{buildingTypeCode}")
    public Optional<BuildingTypeCommand> findByIds(@PathVariable @NotNull Long buildingTypeCode) {

        return Optional.ofNullable(buildingTypeService.findBuildingTypeCommandById(buildingTypeCode));
    }

    @PostMapping("/buildingtypes")
    BuildingTypeCommand newBuildingTypeCommand(@RequestBody BuildingTypeCommand newBuildingTypeCommand) {

        BuildingTypeCommand savedCommand = buildingTypeService.saveBuildingTypeCommand(newBuildingTypeCommand);
        return savedCommand;

    }

    @DeleteMapping("/buildingtypes/{buildingTypeCode}")
    void deleteBuildingTypeCommand(@PathVariable Long buildingTypeCode) {
        buildingTypeService.deleteById(buildingTypeCode);
    }

    @PutMapping
    @RequestMapping("/buildingtypes/{buildingTypeCode}")
    BuildingTypeCommand updateBuildingTypeCommand(@RequestBody BuildingTypeCommand newBuildingTypeCommand, @PathVariable Long buildingTypeCode) {

        buildingTypeService.findBuildingTypeCommandById(buildingTypeCode);
        BuildingTypeCommand savedCommand = buildingTypeService.saveBuildingTypeCommand(newBuildingTypeCommand);
        return savedCommand;
    }
}

