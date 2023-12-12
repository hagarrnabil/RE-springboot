package com.example.respringboot.services;

import com.example.respringboot.commands.UnitOrientationCommand;
import com.example.respringboot.model.UnitOrientation;

import java.util.Set;

public interface UnitOrientationService {
    Set<UnitOrientationCommand> getUnitOrientationCommands();

    UnitOrientation findById(Long l);

    void deleteById(Long idToDelete);

    UnitOrientationCommand saveUnitOrientationCommand(UnitOrientationCommand command);

    UnitOrientationCommand findUnitOrientationCommandById(Long l);
}
