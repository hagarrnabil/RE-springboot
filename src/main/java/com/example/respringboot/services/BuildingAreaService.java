package com.example.respringboot.services;

import com.example.respringboot.commands.BuildingAreaCommand;
import com.example.respringboot.model.BuildingArea;
import com.example.respringboot.model.Company;

import java.util.Set;

public interface BuildingAreaService {
    Set<BuildingAreaCommand> getBuildingAreaCommands();

    BuildingArea findById(Long l);

    void deleteById(Long idToDelete);

    BuildingAreaCommand saveBuildingAreaCommand(BuildingAreaCommand command);
    BuildingArea updateBuildingArea(BuildingArea buildingArea, Long l);

    BuildingAreaCommand findBuildingAreaCommandById(Long l);
}
