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
    public Unit updateUnit(UnitCommand newUnitCommand, Long l) {
        return unitRepository.findById(l).map(oldUnit -> {
            if (newUnitCommand.getUnitKey() != oldUnit.getUnitKey()) oldUnit.setUnitKey(newUnitCommand.getUnitKey());
            if (newUnitCommand.getDescription() != oldUnit.getDescription()) oldUnit.setDescription(newUnitCommand.getDescription());
            if (newUnitCommand.getOldNumber() != oldUnit.getOldNumber()) oldUnit.setOldNumber(newUnitCommand.getOldNumber());
            if (newUnitCommand.getBlockingDate() != oldUnit.getBlockingDate()) oldUnit.setBlockingDate(newUnitCommand.getBlockingDate());
            if (newUnitCommand.getBlockingReason() != oldUnit.getBlockingReason()) oldUnit.setBlockingReason(newUnitCommand.getBlockingReason());
            if (newUnitCommand.getSalesPhase() != oldUnit.getSalesPhase()) oldUnit.setSalesPhase(newUnitCommand.getSalesPhase());
            if (newUnitCommand.getConstructionDate() != oldUnit.getConstructionDate()) oldUnit.setConstructionDate(newUnitCommand.getConstructionDate());
            if (newUnitCommand.getUnitDeliveryDate() != oldUnit.getUnitDeliveryDate()) oldUnit.setUnitDeliveryDate(newUnitCommand.getUnitDeliveryDate());
            if (newUnitCommand.getArea() != oldUnit.getArea()) oldUnit.setArea(newUnitCommand.getArea());
            if (newUnitCommand.getAreaValue() != oldUnit.getAreaValue()) oldUnit.setAreaValue(newUnitCommand.getAreaValue());
            if (newUnitCommand.getPrice() != oldUnit.getPrice()) oldUnit.setPrice(newUnitCommand.getPrice());
            if (newUnitCommand.getNoOfRooms() != oldUnit.getNoOfRooms()) oldUnit.setNoOfRooms(newUnitCommand.getNoOfRooms());
            if (newUnitCommand.getValidFrom() != oldUnit.getValidFrom()) oldUnit.setValidFrom(newUnitCommand.getValidFrom());
            if (newUnitCommand.getFromFloor() != oldUnit.getFromFloor()) oldUnit.setFromFloor(newUnitCommand.getFromFloor());
            if (newUnitCommand.getToFloor() != oldUnit.getToFloor()) oldUnit.setToFloor(newUnitCommand.getToFloor());
//            if (newUnitCommand.getBuilding() != null) oldUnit.getBuilding().addUnit(newUnitCommand);
//            if (newUnitCommand.getUnitFloor() != null) oldUnit.getUnitFloor().addUnit(newUnitCommand);
//            if (newUnitCommand.getUnitOrientation() != null) oldUnit.getUnitOrientation().addUnit(newUnitCommand);
//            if (newUnitCommand.getUnitFixture() != null) oldUnit.getUnitFixture().addUnit(newUnitCommand);
//            if (newUnitCommand.getUnitStatus() != null) oldUnit.getUnitStatus().addUnit(newUnitCommand);
//            if (newUnitCommand.getUnitView() != null) oldUnit.getUnitView().addUnit(newUnitCommand);
//            if (newUnitCommand.getUnitSubtype() != null) oldUnit.getUnitSubtype().addUnit(newUnitCommand);
//            if (newUnitCommand.getUsageType() != null) oldUnit.getUsageType().addUnit(newUnitCommand);
//            if (newUnitCommand.getAreaMasterDetail() != null) oldUnit.getAreaMasterDetail().addUnit(newUnitCommand);
            return unitRepository.save(oldUnit);
        }).orElseThrow(() -> new RuntimeException("Unit not found"));
    }

    @Override
    @Transactional
    public UnitCommand findUnitCommandById(Long l) {
        return unitToUnitCommand.convert(findById(l));
    }
}
