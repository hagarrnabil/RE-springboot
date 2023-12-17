package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitViewCommand;
import com.example.respringboot.model.UnitView;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitViewCommandToUnitView implements Converter<UnitViewCommand, UnitView> {
    private final UnitCommandToUnit unitConverter;

    public UnitViewCommandToUnitView(UnitCommandToUnit unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitView convert(UnitViewCommand source) {
        if (source == null) {
            return null;
        }

        final UnitView unitView = new UnitView();
        unitView.setUnitViewCode(source.getId());
        unitView.setuViewId(source.getUViewId());
        unitView.setuViewDescr(source.getUViewDescr());
        if (source.getUnitCommands() != null && source.getUnitCommands().size() > 0){
            source.getUnitCommands()
                    .forEach( unitCommand -> unitView.getUnits().add(unitConverter.convert(unitCommand)));
        }
        return unitView;
    }
}