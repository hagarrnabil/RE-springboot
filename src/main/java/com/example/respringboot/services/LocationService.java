package com.example.respringboot.services;

import com.example.respringboot.commands.LocationCommand;
import com.example.respringboot.model.Location;

import java.util.Set;

public interface LocationService {
    Set<LocationCommand> getLocationCommands();

    Location findById(Long l);

    void deleteById(Long idToDelete);

    LocationCommand saveLocationCommand(LocationCommand command);

    LocationCommand findLocationCommandById(Long l);
}
