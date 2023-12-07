package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitSubtypeCommand;
import com.example.respringboot.model.UnitSubtype;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitSubtypeCommandToUnitSubtype implements Converter<UnitSubtypeCommand, UnitSubtype> {
    private final UnitCommandToUnit unitConverter;

    public UnitSubtypeCommandToUnitSubtype(UnitCommandToUnit unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitSubtype convert(UnitSubtypeCommand source) {
        if (source == null) {
            return null;
        }

        final UnitSubtype unitSubtype = new UnitSubtype();
        unitSubtype.setId(source.getId());
        unitSubtype.setUSubtypeId(source.getUSubtypeId());
        unitSubtype.setUSubtypeDescr(source.getUSubtypeDescr());
        if (source.getUnitCommands() != null && source.getUnitCommands().size() > 0){
            source.getUnitCommands()
                    .forEach( unitCommand -> unitSubtype.getUnits().add(unitConverter.convert(unitCommand)));
        }
        return unitSubtype;
    }
}
