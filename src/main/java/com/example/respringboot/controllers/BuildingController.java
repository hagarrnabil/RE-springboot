package com.example.respringboot.controllers;

import com.example.respringboot.commands.BuildingCommand;
import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.repositories.BuildingRepository;
import com.example.respringboot.services.BuildingService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class BuildingController {
    BuildingRepository buildingRepository;
    private final BuildingService buildingService;

    public BuildingController(BuildingRepository buildingRepository, BuildingService buildingService) {
        this.buildingRepository = buildingRepository;
        this.buildingService = buildingService;
    }

    @GetMapping("/buildings")
    Set<BuildingCommand> all() {
        return buildingService.getBuildingCommands();
    }

    @GetMapping("/buildings/{buildingCode}")
    public Optional<BuildingCommand> findByIds(@PathVariable @NotNull Long buildingCode) {

        return Optional.ofNullable(buildingService.findBuildingCommandById(buildingCode));
    }

    @PostMapping("/buildings")
    BuildingCommand newBuildingCommand(@RequestBody BuildingCommand newBuildingCommand) {

        BuildingCommand savedCommand = buildingService.saveBuildingCommand(newBuildingCommand);
        return savedCommand;

    }

    @DeleteMapping("/buildings/{buildingCode}")
    void deleteBuildingCommand(@PathVariable Long buildingCode) {
        buildingService.deleteById(buildingCode);
    }

    @PutMapping
    @RequestMapping("/buildings/{buildingCode}")
    BuildingCommand updateBuildingCommand(@RequestBody BuildingCommand newBuildingCommand, @PathVariable Long buildingCode) {

        buildingService.findBuildingCommandById(buildingCode);
        BuildingCommand savedCommand = buildingService.saveBuildingCommand(newBuildingCommand);
        return savedCommand;
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/buildings/search")
//    @ResponseBody
//    public List<CompanyCommand> Search(@RequestParam String keyword) {
//
//        return companyRepository.search(keyword);
//    }
}