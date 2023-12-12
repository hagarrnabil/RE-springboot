package com.example.respringboot.services;

import com.example.respringboot.commands.BuildingTypeCommand;
import com.example.respringboot.model.BuildingType;

import java.util.Set;

public interface BuildingTypeService {
    Set<BuildingTypeCommand> getBuildingTypeCommands();

    BuildingType findById(Long l);

    void deleteById(Long idToDelete);

    BuildingTypeCommand saveBuildingTypeCommand(BuildingTypeCommand command);

    BuildingTypeCommand findBuildingTypeCommandById(Long l);
}
