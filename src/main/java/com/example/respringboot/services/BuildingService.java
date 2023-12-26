package com.example.respringboot.services;


import com.example.respringboot.commands.BuildingCommand;
import com.example.respringboot.model.Building;

import java.util.Set;

public interface BuildingService {
    Set<BuildingCommand> getBuildingCommands();

    Building findById(Long l);

    void deleteById(Long idToDelete);

    BuildingCommand saveBuildingCommand(BuildingCommand command);
    Building updateBuilding(Building newBuilding, Long l);

    BuildingCommand findBuildingCommandById(Long l);
}
