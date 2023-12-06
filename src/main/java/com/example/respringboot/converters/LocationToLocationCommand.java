package com.example.respringboot.converters;

import com.example.respringboot.commands.LocationCommand;
import com.example.respringboot.model.Location;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocationToLocationCommand implements Converter<Location, LocationCommand> {
    private final ProjectToProjectCommand projectConverter;

    public LocationToLocationCommand(ProjectToProjectCommand projectConverter) {
        this.projectConverter = projectConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public LocationCommand convert(Location location) {
        if (location == null) {
            return null;
        }

        LocationCommand locationCommand = new LocationCommand();
        locationCommand.setId(location.getId());
        locationCommand.setLocationId(location.getLocationId());
        locationCommand.setRegionalLocation(location.getRegionalLocation());
        locationCommand.setProjectCommand(projectConverter.convert(location.getProject()));
        return locationCommand;

    }
}
