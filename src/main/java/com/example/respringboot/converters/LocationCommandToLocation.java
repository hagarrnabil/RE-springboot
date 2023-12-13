package com.example.respringboot.converters;

import com.example.respringboot.commands.LocationCommand;
import com.example.respringboot.model.Location;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocationCommandToLocation implements Converter<LocationCommand, Location> {

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
        return location;
    }
}
