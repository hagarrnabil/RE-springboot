package com.example.respringboot.converters;

import com.example.respringboot.commands.UsageTypeCommand;
import com.example.respringboot.model.UsageType;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsageTypeToUsageTypeCommand implements Converter<UsageType, UsageTypeCommand> {
    private final UnitToUnitCommand unitConverter;

    public UsageTypeToUsageTypeCommand(UnitToUnitCommand unitConverter) {
        this.unitConverter = unitConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public UsageTypeCommand convert(UsageType source) {

        if (source == null) {
            return null;
        }

        final UsageTypeCommand usageTypeCommand = new UsageTypeCommand();
        usageTypeCommand.setId(source.getUsageTypeCode());
        usageTypeCommand.setUsageId(source.getUsageId());
        usageTypeCommand.setUsageDescr(source.getUsageDescr());
        if (source.getUnits() != null && source.getUnits().size() > 0){
            source.getUnits()
                    .forEach(unit -> usageTypeCommand.getUnitCommands().add(unitConverter.convert(unit)));
        }
        return usageTypeCommand;
    }
}
