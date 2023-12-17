package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitFloorCommand;
import com.example.respringboot.model.UnitFloor;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitFloorToUnitFloorCommand implements Converter<UnitFloor, UnitFloorCommand> {
    private final UnitToUnitCommand unitConverter;

    public UnitFloorToUnitFloorCommand(UnitToUnitCommand unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitFloorCommand convert(UnitFloor source) {

        if (source == null) {
            return null;
        }

        final UnitFloorCommand unitFloorCommand = new UnitFloorCommand();
        unitFloorCommand.setId(source.getUnitFloorCode());
        unitFloorCommand.setUFloorId(source.getuFloorId());
        unitFloorCommand.setUFloorDescr(source.getuFloorDescr());
        if (source.getUnits() != null && source.getUnits().size() > 0){
            source.getUnits()
                    .forEach(unit -> unitFloorCommand.getUnitCommands().add(unitConverter.convert(unit)));
        }
        return unitFloorCommand;
    }
}
