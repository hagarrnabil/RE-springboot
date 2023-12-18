package com.example.respringboot.services;

import com.example.respringboot.commands.BuildingCommand;
import com.example.respringboot.converters.BuildingCommandToBuilding;
import com.example.respringboot.converters.BuildingToBuildingCommand;
import com.example.respringboot.model.Building;
import com.example.respringboot.repositories.BuildingRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class BuildingServiceImpl implements BuildingService{
    private final BuildingToBuildingCommand buildingToBuildingCommand;
    private final BuildingCommandToBuilding buildingCommandToBuilding;
    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingToBuildingCommand buildingToBuildingCommand, BuildingCommandToBuilding buildingCommandToBuilding, BuildingRepository buildingRepository) {
        this.buildingToBuildingCommand = buildingToBuildingCommand;
        this.buildingCommandToBuilding = buildingCommandToBuilding;
        this.buildingRepository = buildingRepository;
    }

    @Override
    @Transactional
    public Set<BuildingCommand> getBuildingCommands() {
        return StreamSupport.stream(buildingRepository.findAll()
                        .spliterator(), false)
                .map(buildingToBuildingCommand::convert)
                .collect(Collectors.toSet());

    }

    @Override
    public Building findById(Long l) {
        Optional<Building> buildingOptional = buildingRepository.findById(l);

        if (!buildingOptional.isPresent()) {
            throw new RuntimeException("Building Not Found!");
        }

        return buildingOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        buildingRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public BuildingCommand saveBuildingCommand(BuildingCommand command) {

        Building detachedBuilding = buildingCommandToBuilding.convert(command);
        Building savedBuilding = buildingRepository.save(detachedBuilding);
        log.debug("Saved Building Id:" + savedBuilding.getBuildingCode());
        return buildingToBuildingCommand.convert(savedBuilding);

    }

    @Override
    public Building updateBuilding(Building building, Long l) {
        return buildingRepository.findById(l).map(building1 -> {
            building1.setBuildingCode(building.getBuildingCode());
            building1.setBuildingId(building.getBuildingId());
            building1.setBuildingDescription(building.getBuildingDescription());
            building1.setProfit(building.getProfit());
            building1.setNumberOfFloors(building.getNumberOfFloors());
            building1.setOldNumber(building.getOldNumber());
            building1.setValidFrom(building.getValidFrom());
            building1.getProfitCenter().addBuilding(building);
            building1.getProject().addBuilding(building);
            building1.getBuildingType().addBuilding(building);
            return buildingRepository.save(building);
        }).orElseGet(() -> {
            building.setBuildingCode(l);
            return buildingRepository.save(building);
        });
    }

    @Override
    @Transactional
    public BuildingCommand findBuildingCommandById(Long l) {
        return buildingToBuildingCommand.convert(findById(l));
    }
}
