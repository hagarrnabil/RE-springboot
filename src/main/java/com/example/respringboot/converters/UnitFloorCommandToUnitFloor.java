package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitFloorCommand;
import com.example.respringboot.model.UnitFloor;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitFloorCommandToUnitFloor implements Converter<UnitFloorCommand, UnitFloor> {
    private final UnitCommandToUnit unitConverter;

    public UnitFloorCommandToUnitFloor(UnitCommandToUnit unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitFloor convert(UnitFloorCommand source) {
        if (source == null) {
            return null;
        }

        final UnitFloor unitFloor = new UnitFloor();
        unitFloor.setUnitFloorCode(source.getId());
        unitFloor.setUFloorId(source.getUFloorId());
        unitFloor.setUFloorDescr(source.getUFloorDescr());
        if (source.getUnitCommands() != null && source.getUnitCommands().size() > 0){
            source.getUnitCommands()
                    .forEach( unitCommand -> unitFloor.getUnits().add(unitConverter.convert(unitCommand)));
        }
        return unitFloor;
    }
}
