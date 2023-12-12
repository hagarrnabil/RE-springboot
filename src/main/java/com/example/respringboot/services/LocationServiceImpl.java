package com.example.respringboot.services;

import com.example.respringboot.commands.LocationCommand;
import com.example.respringboot.converters.LocationCommandToLocation;
import com.example.respringboot.converters.LocationToLocationCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.Location;
import com.example.respringboot.repositories.LocationRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public LocationCommand findLocationCommandById(Long l) {
        return locationToLocationCommand.convert(findById(l));
    }
}
