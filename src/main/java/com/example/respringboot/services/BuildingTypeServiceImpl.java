package com.example.respringboot.services;

import com.example.respringboot.commands.BuildingTypeCommand;
import com.example.respringboot.converters.BuildingTypeCommandToBuildingType;
import com.example.respringboot.converters.BuildingTypeToBuildingTypeCommand;
import com.example.respringboot.model.BuildingType;
import com.example.respringboot.repositories.BuildingTypeRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class
BuildingTypeServiceImpl implements BuildingTypeService{
    private final BuildingTypeToBuildingTypeCommand buildingTypeToBuildingTypeCommand;
    private final BuildingTypeCommandToBuildingType buildingTypeCommandToBuildingType;
    private final BuildingTypeRepository buildingTypeRepository;

    public BuildingTypeServiceImpl(BuildingTypeToBuildingTypeCommand buildingTypeToBuildingTypeCommand,
                                   BuildingTypeCommandToBuildingType buildingTypeCommandToBuildingType,
                                   BuildingTypeRepository buildingTypeRepository)
    {
        this.buildingTypeToBuildingTypeCommand = buildingTypeToBuildingTypeCommand;
        this.buildingTypeCommandToBuildingType = buildingTypeCommandToBuildingType;
        this.buildingTypeRepository = buildingTypeRepository;
    }

    @Override
    @Transactional
    public Set<BuildingTypeCommand> getBuildingTypeCommands() {
        return StreamSupport.stream(buildingTypeRepository.findAll()
                        .spliterator(), false)
                .map(buildingTypeToBuildingTypeCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public BuildingType findById(Long l) {
        Optional<BuildingType> buildingTypeOptional = buildingTypeRepository.findById(l);

        if (!buildingTypeOptional.isPresent()) {
            throw new RuntimeException("Building Type Not Found!");
        }

        return buildingTypeOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        buildingTypeRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public BuildingTypeCommand saveBuildingTypeCommand(BuildingTypeCommand command) {

        BuildingType detachedBuildingType = buildingTypeCommandToBuildingType.convert(command);
        BuildingType savedBuildingType = buildingTypeRepository.save(detachedBuildingType);
        log.debug("Saved Building Type Id:" + savedBuildingType.getBuildingTypeCode());
        return buildingTypeToBuildingTypeCommand.convert(savedBuildingType);

    }

    @Override
    @Transactional
    public BuildingType updateBuildingType(BuildingType buildingType, Long l) {
        return buildingTypeRepository.findById(l).map(buildingType1 -> {
            buildingType1.setBuildingTypeCode(buildingType.getBuildingTypeCode());
            buildingType1.setBuildingTypeId(buildingType.getBuildingTypeId());
            buildingType1.setBuildingTypeDescr(buildingType.getBuildingTypeDescr());
            return buildingTypeRepository.save(buildingType);
        }).orElseGet(() -> {
            buildingType.setBuildingTypeCode(l);
            return buildingTypeRepository.save(buildingType);
        });
    }

    @Override
    @Transactional
    public BuildingTypeCommand findBuildingTypeCommandById(Long l) {
        return buildingTypeToBuildingTypeCommand.convert(findById(l));
    }
}
