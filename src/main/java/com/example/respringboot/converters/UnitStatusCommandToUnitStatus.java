package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitStatusCommand;
import com.example.respringboot.model.UnitStatus;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitStatusCommandToUnitStatus implements Converter<UnitStatusCommand, UnitStatus> {
    private final UnitCommandToUnit unitConverter;

    public UnitStatusCommandToUnitStatus(UnitCommandToUnit unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitStatus convert(UnitStatusCommand source) {
        if (source == null) {
            return null;
        }

        final UnitStatus unitStatus = new UnitStatus();
        unitStatus.setUnitStatusCode(source.getId());
        unitStatus.setuStatusId(source.getUStatusId());
        unitStatus.setuStatusDescr(source.getUStatusDescr());
        if (source.getUnitCommands() != null && source.getUnitCommands().size() > 0){
            source.getUnitCommands()
                    .forEach( unitCommand -> unitStatus.getUnits().add(unitConverter.convert(unitCommand)));
        }
        return unitStatus;
    }

}
