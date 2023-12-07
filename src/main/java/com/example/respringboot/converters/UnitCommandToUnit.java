package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitCommand;
import com.example.respringboot.model.Unit;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitCommandToUnit implements Converter<UnitCommand, Unit> {

    @Synchronized
    @Nullable
    @Override
    public Unit convert(UnitCommand source) {
        if (source == null) {
            return null;
        }

        final Unit unit = new Unit();
        unit.setId(source.getId());
        unit.setUnitKey(source.getUnitKey());
        unit.setOldNumber(source.getOldNumber());
        unit.setDescription(source.getDescription());
        unit.setBlockingDate(source.getBlockingDate());
        unit.setBlockingReason(source.getBlockingReason());
        unit.setSalesPhase(source.getSalesPhase());
        unit.setConstructionDate(source.getConstructionDate());
        unit.setUnitDeliveryDate(source.getUnitDeliveryDate());
        unit.setArea(source.getArea());
        unit.setAreaValue(source.getAreaValue());
        unit.setNoOfRooms(source.getNoOfRooms());
        unit.setPrice(source.getPrice());
        unit.setValidFrom(source.getValidFrom());
        unit.setFromFloor(source.getFromFloor());
        unit.setToFloor(source.getToFloor());
        return unit;
    }

}
