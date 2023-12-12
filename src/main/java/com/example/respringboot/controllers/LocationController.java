package com.example.respringboot.controllers;

import com.example.respringboot.commands.LocationCommand;
import com.example.respringboot.repositories.LocationRepository;
import com.example.respringboot.services.LocationService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class LocationController {
    LocationRepository locationRepository;
    private final LocationService locationService;

    public LocationController(LocationRepository locationRepository, LocationService locationService) {
        this.locationRepository = locationRepository;
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    Set<LocationCommand> all() {
        return locationService.getLocationCommands();
    }

    @GetMapping("/locations/{locationCode}")
    public Optional<LocationCommand> findByIds(@PathVariable @NotNull Long locationCode) {

        return Optional.ofNullable(locationService.findLocationCommandById(locationCode));
    }

    @PostMapping("/locations")
    LocationCommand newLocationCommand(@RequestBody LocationCommand newLocationCommand) {

        LocationCommand savedCommand = locationService.saveLocationCommand(newLocationCommand);
        return savedCommand;

    }

    @DeleteMapping("/locations/{locationCode}")
    void deleteLocationCommand(@PathVariable Long locationCode) {
        locationService.deleteById(locationCode);
    }

    @PutMapping
    @RequestMapping("/locations/{locationCode}")
    LocationCommand updateLocationCommand(@RequestBody LocationCommand newLocationCommand, @PathVariable Long locationCode) {

        locationService.findLocationCommandById(locationCode);
        LocationCommand savedCommand = locationService.saveLocationCommand(newLocationCommand);
        return savedCommand;
    }
}