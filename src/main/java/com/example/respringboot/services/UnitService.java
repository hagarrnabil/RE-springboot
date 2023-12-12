package com.example.respringboot.services;


import com.example.respringboot.commands.UnitCommand;
import com.example.respringboot.model.Unit;

import java.util.Set;

public interface UnitService {
    Set<UnitCommand> getUnitCommands();

    Unit findById(Long l);

    void deleteById(Long idToDelete);

    UnitCommand saveUnitCommand(UnitCommand command);

    UnitCommand findUnitCommandById(Long l);
}
