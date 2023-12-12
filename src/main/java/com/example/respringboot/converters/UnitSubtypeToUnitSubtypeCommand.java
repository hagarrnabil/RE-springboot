package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitOrientationCommand;
import com.example.respringboot.commands.UnitSubtypeCommand;
import com.example.respringboot.model.UnitOrientation;
import com.example.respringboot.model.UnitSubtype;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitSubtypeToUnitSubtypeCommand implements Converter<UnitSubtype, UnitSubtypeCommand> {
    private final UnitToUnitCommand unitConverter;

    public UnitSubtypeToUnitSubtypeCommand(UnitToUnitCommand unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitSubtypeCommand convert(UnitSubtype source) {

        if (source == null) {
            return null;
        }

        final UnitSubtypeCommand unitSubtypeCommand = new UnitSubtypeCommand();
        unitSubtypeCommand.setId(source.getUnitSubtypeCode());
        unitSubtypeCommand.setUSubtypeId(source.getUSubtypeId());
        unitSubtypeCommand.setUSubtypeDescr(source.getUSubtypeDescr());
        if (source.getUnits() != null && source.getUnits().size() > 0){
            source.getUnits()
                    .forEach(unit -> unitSubtypeCommand.getUnitCommands().add(unitConverter.convert(unit)));
        }
        return unitSubtypeCommand;
    }
}
