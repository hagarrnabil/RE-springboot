package com.example.respringboot.services;

import com.example.respringboot.commands.LocationCommand;
import com.example.respringboot.converters.LocationCommandToLocation;
import com.example.respringboot.converters.LocationToLocationCommand;
import com.example.respringboot.model.Location;
import com.example.respringboot.repositories.LocationRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class LocationServiceImpl implements LocationService{
    private final LocationToLocationCommand locationToLocationCommand;
    private final LocationCommandToLocation locationCommandToLocation;
    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationToLocationCommand locationToLocationCommand, LocationCommandToLocation locationCommandToLocation, LocationRepository locationRepository) {
        this.locationToLocationCommand = locationToLocationCommand;
        this.locationCommandToLocation = locationCommandToLocation;
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public Set<LocationCommand> getLocationCommands() {
        return StreamSupport.stream(locationRepository.findAll()
                        .spliterator(), false)
                .map(locationToLocationCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public Location findById(Long l) {
        Optional<Location> locationOptional = locationRepository.findById(l);

        if (!locationOptional.isPresent()) {
            throw new RuntimeException("Location Not Found!");
        }

        return locationOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        locationRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public LocationCommand saveLocationCommand(LocationCommand command) {

        Location detachedLocation = locationCommandToLocation.convert(command);
        Location savedLocation = locationRepository.save(detachedLocation);
        log.debug("Saved Location Id:" + savedLocation.getLocationCode());
        return locationToLocationCommand.convert(savedLocation);
    }

    @Override
    public Location updateLocation(Location location, Long l) {
        return locationRepository.findById(l).map(location1 -> {
            location1.setLocationCode(location.getLocationCode());
            location1.setLocationId(location.getLocationId());
            location1.setRegionalLocation(location.getRegionalLocation());
            return locationRepository.save(location);
        }).orElseGet(() -> {
            location.setLocationCode(l);
            return locationRepository.save(location);
        });
    }

    @Override
    @Transactional
    public LocationCommand findLocationCommandById(Long l) {
        return locationToLocationCommand.convert(findById(l));
    }
}
