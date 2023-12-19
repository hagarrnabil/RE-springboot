package com.example.respringboot.services;

import com.example.respringboot.commands.BuildingAreaCommand;
import com.example.respringboot.converters.BuildingAreaCommandToBuildingArea;
import com.example.respringboot.converters.BuildingAreaToBuildingAreaCommand;
import com.example.respringboot.model.BuildingArea;
import com.example.respringboot.repositories.BuildingAreaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class BuildingAreaServiceImpl implements BuildingAreaService{
    private final BuildingAreaToBuildingAreaCommand buildingAreaToBuildingAreaCommand;
    private final BuildingAreaCommandToBuildingArea buildingAreaCommandToBuildingArea;
    private final BuildingAreaRepository buildingAreaRepository;

    public BuildingAreaServiceImpl(BuildingAreaToBuildingAreaCommand buildingAreaToBuildingAreaCommand,
                                   BuildingAreaCommandToBuildingArea buildingAreaCommandToBuildingArea,
                                   BuildingAreaRepository buildingAreaRepository)
    {
        this.buildingAreaToBuildingAreaCommand = buildingAreaToBuildingAreaCommand;
        this.buildingAreaCommandToBuildingArea = buildingAreaCommandToBuildingArea;
        this.buildingAreaRepository = buildingAreaRepository;
    }

    @Override
    @Transactional
    public Set<BuildingAreaCommand> getBuildingAreaCommands() {
        return StreamSupport.stream(buildingAreaRepository.findAll()
                        .spliterator(), false)
                .map(buildingAreaToBuildingAreaCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public BuildingArea findById(Long l) {
        Optional<BuildingArea> buildingAreaOptional = buildingAreaRepository.findById(l);

        if (!buildingAreaOptional.isPresent()) {
            throw new RuntimeException("Building Area Not Found!");
        }

        return buildingAreaOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        buildingAreaRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public BuildingAreaCommand saveBuildingAreaCommand(BuildingAreaCommand command) {

        BuildingArea detachedBuildingArea = buildingAreaCommandToBuildingArea.convert(command);
        BuildingArea savedBuildingArea = buildingAreaRepository.save(detachedBuildingArea);
        log.debug("Saved Building Area Id:" + savedBuildingArea.getBuildingAreaCode());
        return buildingAreaToBuildingAreaCommand.convert(savedBuildingArea);

    }

    @Override
    public BuildingArea updateBuildingArea(BuildingArea buildingArea, Long l) {
        return buildingAreaRepository.findById(l).map(buildingArea1 -> {
            if (buildingArea.getBuildingAreaCode() != null) buildingArea1.setBuildingAreaCode(buildingArea.getBuildingAreaCode());
            if (buildingArea.getBuildingArea() != null) buildingArea1.setBuildingArea(buildingArea.getBuildingArea());
            if (buildingArea.getDescription() != null) buildingArea1.setDescription(buildingArea.getDescription());
            return buildingAreaRepository.save(buildingArea);
        }).orElseGet(() -> {
            buildingArea.setBuildingAreaCode(l);
            return buildingAreaRepository.save(buildingArea);
        });
    }

    @Override
    @Transactional
    public BuildingAreaCommand findBuildingAreaCommandById(Long l) {
        return buildingAreaToBuildingAreaCommand.convert(findById(l));
    }
}
