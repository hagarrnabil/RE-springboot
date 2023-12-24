package com.example.respringboot.services;

import com.example.respringboot.commands.UnitFloorCommand;
import com.example.respringboot.model.UnitFloor;

import java.util.Set;

public interface UnitFloorService {
    Set<UnitFloorCommand> getUnitFloorCommands();

    UnitFloor findById(Long l);

    void deleteById(Long idToDelete);

    UnitFloorCommand saveUnitFloorCommand(UnitFloorCommand command);
    UnitFloor updateUnitFloor(UnitFloorCommand newUnitFloorCommand, Long l);

    UnitFloorCommand findUnitFloorCommandById(Long l);
}
