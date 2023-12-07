package com.example.respringboot.converters;

import com.example.respringboot.commands.BuildingCommand;
import com.example.respringboot.model.Building;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BuildingCommandToBuilding implements Converter<BuildingCommand, Building> {
    private final UnitCommandToUnit unitConverter;

    public BuildingCommandToBuilding(UnitCommandToUnit unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Building convert(BuildingCommand source) {
        if (source == null) {
            return null;
        }

        final Building building = new Building();
        building.setId(source.getId());
        building.setBuildingId(source.getBuildingId());
        building.setBuildingDescription(source.getBuildingDescription());
        building.setOldNumber(source.getOldNumber());
        building.setValidFrom(source.getValidFrom());
        building.setNumberOfFloors(source.getNumberOfFloors());
        building.setProfit(source.getProfit());
        if (source.getUnitCommands() != null && source.getUnitCommands().size() > 0){
            source.getUnitCommands()
                    .forEach( unitCommand -> building.getUnits().add(unitConverter.convert(unitCommand)));
        }
        return building;
    }

}
