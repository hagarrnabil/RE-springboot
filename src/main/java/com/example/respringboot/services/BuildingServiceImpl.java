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
    public Building updateBuilding(BuildingCommand newBuildingCommand, Long l) {
        return buildingRepository.findById(l).map(oldBuilding -> {
            if(newBuildingCommand.getBuildingId() != oldBuilding.getBuildingId()) oldBuilding.setBuildingId(newBuildingCommand.getBuildingId());
            if(newBuildingCommand.getBuildingDescription() != oldBuilding.getBuildingDescription()) oldBuilding.setBuildingDescription(newBuildingCommand.getBuildingDescription());
            if (newBuildingCommand.getProfit() != oldBuilding.getProfit()) oldBuilding.setProfit(newBuildingCommand.getProfit());
            if(newBuildingCommand.getNumberOfFloors() != oldBuilding.getNumberOfFloors()) oldBuilding.setNumberOfFloors(newBuildingCommand.getNumberOfFloors());
            if (newBuildingCommand.getOldNumber() != oldBuilding.getOldNumber()) oldBuilding.setOldNumber(newBuildingCommand.getOldNumber());
            if (newBuildingCommand.getValidFrom() != oldBuilding.getValidFrom()) oldBuilding.setValidFrom(newBuildingCommand.getValidFrom());
//            if (newBuildingCommand.getProfitCenter() != null) oldBuilding.getProfitCenter().addBuilding(newBuildingCommand);
//            if (newBuildingCommand.getProject() != null) oldBuilding.getProject().addBuilding(newBuildingCommand);
//            if (newBuildingCommand.getBuildingType() != null) oldBuilding.getBuildingType().addBuilding(newBuildingCommand);
            return buildingRepository.save(oldBuilding);
        }).orElseThrow(() -> new RuntimeException("Building not found"));
    }

    @Override
    @Transactional
    public BuildingCommand findBuildingCommandById(Long l) {
        return buildingToBuildingCommand.convert(findById(l));
    }
}
