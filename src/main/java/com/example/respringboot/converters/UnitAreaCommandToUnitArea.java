package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitAreaCommand;
import com.example.respringboot.model.UnitArea;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitAreaCommandToUnitArea implements Converter<UnitAreaCommand, UnitArea> {
    private final AreaMasterDetailCommandToAreaMasterDetail areaConverter;

    public UnitAreaCommandToUnitArea(AreaMasterDetailCommandToAreaMasterDetail areaConverter) {
        this.areaConverter = areaConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitArea convert(UnitAreaCommand source) {
        if (source == null) {
            return null;
        }

        final UnitArea unitArea = new UnitArea();
        unitArea.setId(source.getId());
        unitArea.setUnitArea(source.getUnitArea());
        unitArea.setDescription(source.getDescription());
        if (source.getAreaMasterDetailCommands() != null && source.getAreaMasterDetailCommands().size() > 0){
            source.getAreaMasterDetailCommands()
                    .forEach( areaMasterDetailCommand -> unitArea.getAreaMasterDetails().add(areaConverter.convert(areaMasterDetailCommand)));
        }
        return unitArea;
    }
}
