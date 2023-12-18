package com.example.respringboot.services;

import com.example.respringboot.commands.UnitCommand;
import com.example.respringboot.converters.UnitCommandToUnit;
import com.example.respringboot.converters.UnitToUnitCommand;
import com.example.respringboot.model.Unit;
import com.example.respringboot.repositories.UnitRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitServiceImpl implements UnitService{
    private final UnitToUnitCommand unitToUnitCommand;
    private final UnitCommandToUnit unitCommandToUnit;
    private final UnitRepository unitRepository;

    public UnitServiceImpl(UnitToUnitCommand unitToUnitCommand, UnitCommandToUnit unitCommandToUnit, UnitRepository unitRepository) {
        this.unitToUnitCommand = unitToUnitCommand;
        this.unitCommandToUnit = unitCommandToUnit;
        this.unitRepository = unitRepository;
    }

    @Override
    @Transactional
    public Set<UnitCommand> getUnitCommands() {
        return StreamSupport.stream(unitRepository.findAll()
                        .spliterator(), false)
                .map(unitToUnitCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public Unit findById(Long l) {
        Optional<Unit> unitOptional = unitRepository.findById(l);

        if (!unitOptional.isPresent()) {
            throw new RuntimeException("Unit Not Found!");
        }

        return unitOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        unitRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public UnitCommand saveUnitCommand(UnitCommand command) {

        Unit detachedUnit = unitCommandToUnit.convert(command);
        Unit savedUnit = unitRepository.save(detachedUnit);
        log.debug("Saved Unit Id:" + savedUnit.getUnitCode());
        return unitToUnitCommand.convert(savedUnit);

    }

    @Override
    public Unit updateUnit(Unit unit, Long l) {
        return unitRepository.findById(l).map(unit1 -> {
            unit1.setUnitCode(unit.getUnitCode());
            unit1.setUnitKey(unit.getUnitKey());
            unit1.setDescription(unit.getDescription());
            unit1.setOldNumber(unit.getOldNumber());
            unit1.setBlockingDate(unit.getBlockingDate());
            unit1.setBlockingReason(unit.getBlockingReason());
            unit1.setSalesPhase(unit.getSalesPhase());
            unit1.setConstructionDate(unit.getConstructionDate());
            unit1.setUnitDeliveryDate(unit.getUnitDeliveryDate());
            unit1.setArea(unit.getArea());
            unit1.setAreaValue(unit.getAreaValue());
            unit1.setPrice(unit.getPrice());
            unit1.setNoOfRooms(unit.getNoOfRooms());
            unit1.setValidFrom(unit.getValidFrom());
            unit1.setFromFloor(unit.getFromFloor());
            unit1.setToFloor(unit.getToFloor());
            unit1.getBuilding().addUnit(unit);
            unit1.getUnitFloor().addUnit(unit);
            unit1.getUnitOrientation().addUnit(unit);
            unit1.getUnitFixture().addUnit(unit);
            unit1.getUnitStatus().addUnit(unit);
            unit1.getUnitView().addUnit(unit);
            unit1.getUnitSubtype().addUnit(unit);
            unit1.getUsageType().addUnit(unit);
            unit1.getAreaMasterDetail().addUnit(unit);
            return unitRepository.save(unit);
        }).orElseGet(() -> {
            unit.setUnitCode(l);
            return unitRepository.save(unit);
        });
    }

    @Override
    @Transactional
    public UnitCommand findUnitCommandById(Long l) {
        return unitToUnitCommand.convert(findById(l));
    }
}
