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
            if (unit.getUnitCode() != null) unit1.setUnitCode(unit.getUnitCode());
            if (unit.getUnitKey() != null) unit1.setUnitKey(unit.getUnitKey());
            if (unit.getDescription() != null) unit1.setDescription(unit.getDescription());
            if (unit.getOldNumber() != null) unit1.setOldNumber(unit.getOldNumber());
            if (unit.getBlockingDate() != null) unit1.setBlockingDate(unit.getBlockingDate());
            if (unit.getBlockingReason() != null) unit1.setBlockingReason(unit.getBlockingReason());
            if (unit.getSalesPhase() != null) unit1.setSalesPhase(unit.getSalesPhase());
            if (unit.getConstructionDate() != null) unit1.setConstructionDate(unit.getConstructionDate());
            if (unit.getUnitDeliveryDate() != null) unit1.setUnitDeliveryDate(unit.getUnitDeliveryDate());
            if (unit.getArea() != null) unit1.setArea(unit.getArea());
            if (unit.getAreaValue() != null) unit1.setAreaValue(unit.getAreaValue());
            if (unit.getPrice() != null) unit1.setPrice(unit.getPrice());
            if (unit.getNoOfRooms() != null) unit1.setNoOfRooms(unit.getNoOfRooms());
            if (unit.getValidFrom() != null) unit1.setValidFrom(unit.getValidFrom());
            if (unit.getFromFloor() != null) unit1.setFromFloor(unit.getFromFloor());
            if (unit.getToFloor() != null) unit1.setToFloor(unit.getToFloor());
            if (unit.getBuilding() != null) unit1.getBuilding().addUnit(unit);
            if (unit.getUnitFloor() != null) unit1.getUnitFloor().addUnit(unit);
            if (unit.getUnitOrientation() != null) unit1.getUnitOrientation().addUnit(unit);
            if (unit.getUnitFixture() != null) unit1.getUnitFixture().addUnit(unit);
            if (unit.getUnitStatus() != null) unit1.getUnitStatus().addUnit(unit);
            if (unit.getUnitView() != null) unit1.getUnitView().addUnit(unit);
            if (unit.getUnitSubtype() != null) unit1.getUnitSubtype().addUnit(unit);
            if (unit.getUsageType() != null) unit1.getUsageType().addUnit(unit);
            if (unit.getAreaMasterDetail() != null) unit1.getAreaMasterDetail().addUnit(unit);
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
