package com.example.respringboot.converters;

import com.example.respringboot.commands.UnitOfMeasurementCommand;
import com.example.respringboot.model.UnitOfMeasurement;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasurementCommandToUnitOfMeasurement implements Converter<UnitOfMeasurementCommand, UnitOfMeasurement> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasurement convert(UnitOfMeasurementCommand source) {

        if (source == null) {
            return null;
        }

        final UnitOfMeasurement measurement = new UnitOfMeasurement();
        measurement.setId(source.getId());
        measurement.setUomID(source.getUomID());
        measurement.setUomDescr(source.getUomDescr());
        return measurement;

    }
}
