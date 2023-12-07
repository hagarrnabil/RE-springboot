package com.example.respringboot.converters;

import com.example.respringboot.commands.AreaMasterDetailCommand;
import com.example.respringboot.model.AreaMasterDetail;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AreaMasterDetailCommandToAreaMasterDetail implements Converter<AreaMasterDetailCommand, AreaMasterDetail> {
    private final UnitCommandToUnit unitConverter;
    private final UnitOfMeasurementCommandToUnitOfMeasurement uomConverter;

    public AreaMasterDetailCommandToAreaMasterDetail(UnitCommandToUnit unitConverter, UnitOfMeasurementCommandToUnitOfMeasurement uomConverter) {
        this.unitConverter = unitConverter;
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public AreaMasterDetail convert(AreaMasterDetailCommand source) {
        if (source == null) {
            return null;
        }

        final AreaMasterDetail areaMasterDetail = new AreaMasterDetail();
        areaMasterDetail.setId(source.getId());
        areaMasterDetail.setAreaMaster(source.getAreaMaster());
        areaMasterDetail.setDescription(source.getDescription());
        areaMasterDetail.setProjectFlag(source.getProjectFlag());
        areaMasterDetail.setBuildingFlag(source.getBuildingFlag());
        areaMasterDetail.setUnitFlag(source.getUnitFlag());
        areaMasterDetail.setUnitOfMeasurement(uomConverter.convert(source.getUnitOfMeasurementCommand()));
        if (source.getUnitCommands() != null && source.getUnitCommands().size() > 0){
            source.getUnitCommands()
                    .forEach( unitCommand -> areaMasterDetail.getUnits().add(unitConverter.convert(unitCommand)));
        }
        return areaMasterDetail;
    }

}
