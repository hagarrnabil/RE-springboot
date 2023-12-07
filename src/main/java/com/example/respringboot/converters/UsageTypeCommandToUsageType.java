package com.example.respringboot.converters;

import com.example.respringboot.commands.UsageTypeCommand;
import com.example.respringboot.model.UsageType;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsageTypeCommandToUsageType implements Converter<UsageTypeCommand, UsageType> {
    private final UnitCommandToUnit unitConverter;

    public UsageTypeCommandToUsageType(UnitCommandToUnit unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UsageType convert(UsageTypeCommand source) {
        if (source == null) {
            return null;
        }

        final UsageType usageType = new UsageType();
        usageType.setId(source.getId());
        usageType.setUsageId(source.getUsageId());
        usageType.setUsageDescr(source.getUsageDescr());
        if (source.getUnitCommands() != null && source.getUnitCommands().size() > 0){
            source.getUnitCommands()
                    .forEach( unitCommand -> usageType.getUnits().add(unitConverter.convert(unitCommand)));
        }
        return usageType;
    }
}
