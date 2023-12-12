package com.example.respringboot.services;

import com.example.respringboot.commands.UnitCommand;
import com.example.respringboot.converters.UnitCommandToUnit;
import com.example.respringboot.converters.UnitToUnitCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.Unit;
import com.example.respringboot.repositories.UnitRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public UnitCommand findUnitCommandById(Long l) {
        return unitToUnitCommand.convert(findById(l));
    }
}
