package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitOfMeasurementCommand;
import com.example.respringboot.model.UnitOfMeasurement;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasurementToUnitOfMeasurementCommand implements Converter<UnitOfMeasurement, UnitOfMeasurementCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasurementCommand convert(UnitOfMeasurement source) {
        if (source == null) {
            return null;
        }

        final UnitOfMeasurementCommand measurementCommand = new UnitOfMeasurementCommand();
        measurementCommand.setMeasurementCode(source.getMeasurementCode());
        measurementCommand.setUomID(source.getUomID());
        measurementCommand.setUomDescr(source.getUomDescr());
        return measurementCommand;
    }
}
