package com.example.respringboot.services;

import com.example.respringboot.commands.BuildingTypeCommand;
import com.example.respringboot.model.BuildingType;
import com.example.respringboot.model.Company;

import java.util.Set;

public interface BuildingTypeService {
    Set<BuildingTypeCommand> getBuildingTypeCommands();

    BuildingType findById(Long l);

    void deleteById(Long idToDelete);

    BuildingTypeCommand saveBuildingTypeCommand(BuildingTypeCommand command);
    BuildingType updateBuildingType(BuildingType buildingType, Long l);

    BuildingTypeCommand findBuildingTypeCommandById(Long l);
}
