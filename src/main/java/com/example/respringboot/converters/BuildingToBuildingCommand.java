package com.example.respringboot.converters;

import com.example.respringboot.commands.BuildingCommand;
import com.example.respringboot.model.Building;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BuildingToBuildingCommand implements Converter<Building, BuildingCommand> {
    private final UnitToUnitCommand unitConverter;

    public BuildingToBuildingCommand(UnitToUnitCommand unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public BuildingCommand convert(Building source) {
        if (source == null) {
            return null;
        }

        final BuildingCommand buildingCommand = new BuildingCommand();
        buildingCommand.setId(source.getBuildingCode());
        if (source.getProject() != null) {
            buildingCommand.setProjectCode(source.getProject().getProjectCode());
        }
        if (source.getProfitCenter() != null) {
            buildingCommand.setProfitCode(source.getProfitCenter().getProfitCode());
        }
        if (source.getBuildingType() != null) {
            buildingCommand.setBuildingTypeCode(source.getBuildingType().getBuildingTypeCode());
        }
        buildingCommand.setId(source.getBuildingCode());
        buildingCommand.setBuildingId(source.getBuildingId());
        buildingCommand.setBuildingDescription(source.getBuildingDescription());
        buildingCommand.setOldNumber(source.getOldNumber());
        buildingCommand.setProfit(source.getProfit());
        buildingCommand.setValidFrom(source.getValidFrom());
        buildingCommand.setNumberOfFloors(source.getNumberOfFloors());
        if (source.getUnits() != null && source.getUnits().size() > 0){
            source.getUnits()
                    .forEach(unit -> buildingCommand.getUnitCommands().add(unitConverter.convert(unit)));
        }
        return buildingCommand;
    }

}
