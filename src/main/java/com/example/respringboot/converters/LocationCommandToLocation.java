package com.example.respringboot.converters;

import com.example.respringboot.commands.LocationCommand;
import com.example.respringboot.model.Location;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocationCommandToLocation implements Converter<LocationCommand, Location> {
    private final ProjectCommandToProject projectConverter;

    public LocationCommandToLocation(ProjectCommandToProject projectConverter) {
        this.projectConverter = projectConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Location convert(LocationCommand source) {
        if (source == null) {
            return null;
        }

        final Location location = new Location();
        location.setLocationCode(source.getId());
        location.setLocationId(source.getLocationId());
        location.setRegionalLocation(source.getRegionalLocation());
        location.setProject(projectConverter.convert(source.getProjectCommand()));
        return location;
    }
}
