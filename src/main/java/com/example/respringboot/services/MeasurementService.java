package com.example.respringboot.services;


import com.example.respringboot.commands.UnitOfMeasurementCommand;
import com.example.respringboot.model.UnitOfMeasurement;

import java.util.Set;

public interface MeasurementService {
    Set<UnitOfMeasurementCommand> getUOMCommands();

    UnitOfMeasurement findById(Long l);

    void deleteById(Long idToDelete);

    UnitOfMeasurementCommand saveUOMCommand(UnitOfMeasurementCommand command);
    UnitOfMeasurement updateUOM(UnitOfMeasurementCommand newMeasurementCommand, Long l);

    UnitOfMeasurementCommand findUOMCommandById(Long l);
}
