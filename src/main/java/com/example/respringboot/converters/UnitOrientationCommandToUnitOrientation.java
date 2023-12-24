package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitOrientationCommand;
import com.example.respringboot.model.UnitOrientation;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOrientationCommandToUnitOrientation implements Converter<UnitOrientationCommand, UnitOrientation> {
    private final UnitCommandToUnit unitConverter;

    public UnitOrientationCommandToUnitOrientation(UnitCommandToUnit unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitOrientation convert(UnitOrientationCommand source) {
        if (source == null) {
            return null;
        }

        final UnitOrientation unitOrientation = new UnitOrientation();
        unitOrientation.setUnitOrientationCode(source.getId());
        unitOrientation.setuOrientationId(source.getuOrientationId());
        unitOrientation.setuOrientationDescr(source.getuOrientationDescr());
        if (source.getUnitCommands() != null && source.getUnitCommands().size() > 0){
            source.getUnitCommands()
                    .forEach( unitCommand -> unitOrientation.getUnits().add(unitConverter.convert(unitCommand)));
        }
        return unitOrientation;
    }

}
