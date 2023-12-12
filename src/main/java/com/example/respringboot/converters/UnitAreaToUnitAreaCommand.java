package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitAreaCommand;
import com.example.respringboot.model.UnitArea;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitAreaToUnitAreaCommand implements Converter<UnitArea, UnitAreaCommand> {
    private final AreaMasterDetailToAreaMasterDetailCommand areaConverter;

    public UnitAreaToUnitAreaCommand(AreaMasterDetailToAreaMasterDetailCommand areaConverter) {
        this.areaConverter = areaConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitAreaCommand convert(UnitArea source) {
        if (source == null) {
            return null;
        }

        final UnitAreaCommand unitAreaCommand = new UnitAreaCommand();
        unitAreaCommand.setId(source.getUnitAreaCode());
        unitAreaCommand.setUnitArea(source.getUnitArea());
        unitAreaCommand.setDescription(source.getDescription());
        if (source.getAreaMasterDetails() != null && source.getAreaMasterDetails().size() > 0){
            source.getAreaMasterDetails()
                    .forEach(areaMasterDetail -> unitAreaCommand.getAreaMasterDetailCommands().add(areaConverter.convert(areaMasterDetail)));
        }
        return unitAreaCommand;
    }
}
