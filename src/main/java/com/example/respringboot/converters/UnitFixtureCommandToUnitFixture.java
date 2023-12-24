package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitFixtureCommand;
import com.example.respringboot.commands.UsageTypeCommand;
import com.example.respringboot.model.UnitFixture;
import com.example.respringboot.model.UsageType;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class UnitFixtureCommandToUnitFixture implements Converter<UnitFixtureCommand, UnitFixture> {
    private final UnitCommandToUnit unitConverter;

    public UnitFixtureCommandToUnitFixture(UnitCommandToUnit unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UnitFixture convert(UnitFixtureCommand source) {
        if (source == null) {
            return null;
        }

        final UnitFixture unitFixture = new UnitFixture();
        unitFixture.setUnitFixtureCode(source.getId());
        unitFixture.setuFixtureId(source.getuFixtureId());
        unitFixture.setuFixtureDescr(source.getuFixtureDescr());
        if (source.getUnitCommands() != null && source.getUnitCommands().size() > 0){
            source.getUnitCommands()
                    .forEach( unitCommand -> unitFixture.getUnits().add(unitConverter.convert(unitCommand)));
        }
        return unitFixture;
    }
}
