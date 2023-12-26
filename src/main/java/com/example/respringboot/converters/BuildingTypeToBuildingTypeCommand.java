package com.example.respringboot.converters;

import com.example.respringboot.commands.BuildingTypeCommand;
import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.model.BuildingType;
import com.example.respringboot.model.Company;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BuildingTypeToBuildingTypeCommand implements Converter<BuildingType, BuildingTypeCommand> {
    private final BuildingToBuildingCommand buildingConverter;

    public BuildingTypeToBuildingTypeCommand(BuildingToBuildingCommand buildingConverter) {
        this.buildingConverter = buildingConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public BuildingTypeCommand convert(BuildingType source) {

        if (source == null) {
            return null;
        }

        final BuildingTypeCommand buildingTypeCommand = new BuildingTypeCommand();
        buildingTypeCommand.setBuildingTypeCode(source.getBuildingTypeCode());
        buildingTypeCommand.setBuildingTypeId(source.getBuildingTypeId());
        buildingTypeCommand.setBuildingTypeDescr(source.getBuildingTypeDescr());
        if (source.getBuildings() != null && source.getBuildings().size() > 0) {
            source.getBuildings()
                    .forEach(building -> buildingTypeCommand.getBuildingCommands().add(buildingConverter.convert(building)));
        }
        return buildingTypeCommand;
    }

}
