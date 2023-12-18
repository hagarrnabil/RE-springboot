package com.example.respringboot.controllers;

import com.example.respringboot.commands.BuildingTypeCommand;
import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.converters.BuildingTypeToBuildingTypeCommand;
import com.example.respringboot.model.BuildingType;
import com.example.respringboot.model.Company;
import com.example.respringboot.repositories.BuildingTypeRepository;
import com.example.respringboot.services.BuildingTypeService;
import jakarta.validation.constraints.NotNull;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class BuildingTypeController {
    private final BuildingTypeRepository buildingTypeRepository;
    private final BuildingTypeService buildingTypeService;
    private final BuildingTypeToBuildingTypeCommand buildingTypeToBuildingTypeCommand;

    public BuildingTypeController(BuildingTypeRepository buildingTypeRepository, BuildingTypeService buildingTypeService,
                                  BuildingTypeToBuildingTypeCommand buildingTypeToBuildingTypeCommand) {
        this.buildingTypeRepository = buildingTypeRepository;
        this.buildingTypeService = buildingTypeService;
        this.buildingTypeToBuildingTypeCommand = buildingTypeToBuildingTypeCommand;
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
    @Transactional
    BuildingTypeCommand updateBuildingTypeCommand(@RequestBody BuildingType newBuildingType, @PathVariable Long buildingTypeCode) {

        BuildingTypeCommand command = buildingTypeToBuildingTypeCommand.convert(buildingTypeService.updateBuildingType(newBuildingType, buildingTypeCode));
        return command;
    }
}

