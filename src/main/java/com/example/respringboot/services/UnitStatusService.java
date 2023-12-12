package com.example.respringboot.services;

import com.example.respringboot.commands.UnitStatusCommand;
import com.example.respringboot.model.UnitStatus;

import java.util.Set;

public interface UnitStatusService {
    Set<UnitStatusCommand> getUnitStatusCommands();

    UnitStatus findById(Long l);

    void deleteById(Long idToDelete);

    UnitStatusCommand saveUnitStatusCommand(UnitStatusCommand command);

    UnitStatusCommand findUnitStatusCommandById(Long l);
}
