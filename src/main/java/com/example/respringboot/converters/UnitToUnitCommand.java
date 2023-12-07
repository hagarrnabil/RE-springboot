package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitCommand;
import com.example.respringboot.model.Unit;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitToUnitCommand implements Converter<Unit, UnitCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitCommand convert(Unit source) {
        if (source == null) {
            return null;
        }

        final UnitCommand unitCommand = new UnitCommand();
        unitCommand.setId(source.getId());
        unitCommand.setUnitKey(source.getUnitKey());
        unitCommand.setOldNumber(source.getOldNumber());
        unitCommand.setDescription(source.getDescription());
        unitCommand.setBlockingDate(source.getBlockingDate());
        unitCommand.setBlockingReason(source.getBlockingReason());
        unitCommand.setSalesPhase(source.getSalesPhase());
        unitCommand.setConstructionDate(source.getConstructionDate());
        unitCommand.setUnitDeliveryDate(source.getUnitDeliveryDate());
        unitCommand.setArea(source.getArea());
        unitCommand.setAreaValue(source.getAreaValue());
        unitCommand.setPrice(source.getPrice());
        unitCommand.setNoOfRooms(source.getNoOfRooms());
        unitCommand.setValidFrom(source.getValidFrom());
        unitCommand.setFromFloor(source.getFromFloor());
        unitCommand.setToFloor(source.getToFloor());
        return unitCommand;
    }
}
