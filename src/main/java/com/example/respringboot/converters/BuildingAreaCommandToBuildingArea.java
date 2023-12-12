package com.example.respringboot.converters;

import com.example.respringboot.commands.BuildingAreaCommand;
import com.example.respringboot.model.BuildingArea;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BuildingAreaCommandToBuildingArea implements Converter<BuildingAreaCommand, BuildingArea> {
    private final AreaMasterDetailCommandToAreaMasterDetail areaConverter;

    public BuildingAreaCommandToBuildingArea(AreaMasterDetailCommandToAreaMasterDetail areaConverter) {
        this.areaConverter = areaConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public BuildingArea convert(BuildingAreaCommand source) {
        if (source == null) {
            return null;
        }

        final BuildingArea buildingArea = new BuildingArea();
        buildingArea.setBuildingAreaCode(source.getId());
        buildingArea.setBuildingArea(source.getBuildingArea());
        buildingArea.setDescription(source.getDescription());
        if (source.getAreaMasterDetailCommands() != null && source.getAreaMasterDetailCommands().size() > 0){
            source.getAreaMasterDetailCommands()
                    .forEach( areaMasterDetailCommand -> buildingArea.getAreaMasterDetails().add(areaConverter.convert(areaMasterDetailCommand)));
        }
        return buildingArea;
    }
}
