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
    public LocationCommand convert(Location source) {
        if (source == null) {
            return null;
        }

        final LocationCommand locationCommand = new LocationCommand();
        locationCommand.setId(source.getId());
        locationCommand.setLocationId(source.getLocationId());
        locationCommand.setRegionalLocation(source.getRegionalLocation());
        locationCommand.setProjectCommand(projectConverter.convert(source.getProject()));
        return locationCommand;

    }
}
