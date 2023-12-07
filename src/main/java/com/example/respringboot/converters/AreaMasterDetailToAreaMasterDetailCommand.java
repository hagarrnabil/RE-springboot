package com.example.respringboot.converters;

import com.example.respringboot.commands.AreaMasterDetailCommand;
import com.example.respringboot.model.AreaMasterDetail;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AreaMasterDetailToAreaMasterDetailCommand implements Converter<AreaMasterDetail, AreaMasterDetailCommand> {
    private final UnitToUnitCommand unitConverter;
    private final UnitOfMeasurementToUnitOfMeasurementCommand uomConverter;

    public AreaMasterDetailToAreaMasterDetailCommand(UnitToUnitCommand unitConverter, UnitOfMeasurementToUnitOfMeasurementCommand uomConverter) {
        this.unitConverter = unitConverter;
        this.uomConverter = uomConverter;
    }
    @Synchronized
    @Nullable
    @Override
    public AreaMasterDetailCommand convert(AreaMasterDetail source) {

        if (source == null) {
            return null;
        }

        final AreaMasterDetailCommand areaMasterDetailCommand = new AreaMasterDetailCommand();
        areaMasterDetailCommand.setId(source.getId());
        areaMasterDetailCommand.setAreaMaster(source.getAreaMaster());
        areaMasterDetailCommand.setDescription(source.getDescription());
        areaMasterDetailCommand.setProjectFlag(source.getProjectFlag());
        areaMasterDetailCommand.setBuildingFlag(source.getBuildingFlag());
        areaMasterDetailCommand.setUnitFlag(source.getUnitFlag());
        areaMasterDetailCommand.setUnitOfMeasurementCommand(uomConverter.convert(source.getUnitOfMeasurement()));
        if (source.getUnits() != null && source.getUnits().size() > 0){
            source.getUnits()
                    .forEach(unit -> areaMasterDetailCommand.getUnitCommands().add(unitConverter.convert(unit)));
        }
        return areaMasterDetailCommand;
    }
}
