package com.example.respringboot.services;

import com.example.respringboot.commands.UnitOrientationCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UnitOrientation;

import java.util.Set;

public interface UnitOrientationService {
    Set<UnitOrientationCommand> getUnitOrientationCommands();

    UnitOrientation findById(Long l);

    void deleteById(Long idToDelete);

    UnitOrientationCommand saveUnitOrientationCommand(UnitOrientationCommand command);
    UnitOrientation updateUnitOrientation(UnitOrientation unitOrientation, Long l);

    UnitOrientationCommand findUnitOrientationCommandById(Long l);
}
