package com.example.respringboot.services;

import com.example.respringboot.commands.UnitOfMeasurementCommand;
import com.example.respringboot.converters.UnitOfMeasurementCommandToUnitOfMeasurement;
import com.example.respringboot.converters.UnitOfMeasurementToUnitOfMeasurementCommand;
import com.example.respringboot.model.UnitOfMeasurement;
import com.example.respringboot.repositories.UnitOfMeasurementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class MeasurementServiceImpl implements MeasurementService{
    private final UnitOfMeasurementToUnitOfMeasurementCommand unitOfMeasurementToUnitOfMeasurementCommand;
    private final UnitOfMeasurementCommandToUnitOfMeasurement unitOfMeasurementCommandToUnitOfMeasurement;
    private final UnitOfMeasurementRepository unitOfMeasurementRepository;

    public MeasurementServiceImpl(UnitOfMeasurementToUnitOfMeasurementCommand unitOfMeasurementToUnitOfMeasurementCommand,
                                  UnitOfMeasurementCommandToUnitOfMeasurement unitOfMeasurementCommandToUnitOfMeasurement,
                                  UnitOfMeasurementRepository unitOfMeasurementRepository)
    {
        this.unitOfMeasurementToUnitOfMeasurementCommand = unitOfMeasurementToUnitOfMeasurementCommand;
        this.unitOfMeasurementCommandToUnitOfMeasurement = unitOfMeasurementCommandToUnitOfMeasurement;
        this.unitOfMeasurementRepository = unitOfMeasurementRepository;
    }

    @Override
    @Transactional
    public Set<UnitOfMeasurementCommand> getUOMCommands() {
        return StreamSupport.stream(unitOfMeasurementRepository.findAll()
                        .spliterator(), false)
                .map(unitOfMeasurementToUnitOfMeasurementCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitOfMeasurement findById(Long l) {
        Optional<UnitOfMeasurement> measurementOptional = unitOfMeasurementRepository.findById(l);

        if (!measurementOptional.isPresent()) {
            throw new RuntimeException("UOM Not Found!");
        }

        return measurementOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        unitOfMeasurementRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public UnitOfMeasurementCommand saveUOMCommand(UnitOfMeasurementCommand command) {
        UnitOfMeasurement detachedUOM = unitOfMeasurementCommandToUnitOfMeasurement.convert(command);
        UnitOfMeasurement savedUOM = unitOfMeasurementRepository.save(detachedUOM);
        log.debug("Saved UOM Id:" + savedUOM.getMeasurementCode());
        return unitOfMeasurementToUnitOfMeasurementCommand.convert(savedUOM);
    }

    @Override
    public UnitOfMeasurement updateUOM(UnitOfMeasurementCommand newMeasurementCommand, Long l) {
        return unitOfMeasurementRepository.findById(l).map(oldMeasurement -> {
           if (newMeasurementCommand.getUomID() != oldMeasurement.getUomID()) oldMeasurement.setUomID(newMeasurementCommand.getUomID());
            if (newMeasurementCommand.getUomDescr() != oldMeasurement.getUomDescr()) oldMeasurement.setUomDescr(newMeasurementCommand.getUomDescr());
            return unitOfMeasurementRepository.save(oldMeasurement);
        }).orElseThrow(() -> new RuntimeException("Measurement not found"));
    }

    @Override
    @Transactional
    public UnitOfMeasurementCommand findUOMCommandById(Long l) {
        return unitOfMeasurementToUnitOfMeasurementCommand.convert(findById(l));
    }
}
