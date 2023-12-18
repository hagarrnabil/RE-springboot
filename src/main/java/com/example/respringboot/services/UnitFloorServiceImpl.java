package com.example.respringboot.services;

import com.example.respringboot.commands.UnitFloorCommand;
import com.example.respringboot.converters.UnitFloorCommandToUnitFloor;
import com.example.respringboot.converters.UnitFloorToUnitFloorCommand;
import com.example.respringboot.model.UnitFloor;
import com.example.respringboot.repositories.UnitFloorRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitFloorServiceImpl implements UnitFloorService{
    private final UnitFloorToUnitFloorCommand unitFloorToUnitFloorCommand;
    private final UnitFloorCommandToUnitFloor unitFloorCommandToUnitFloor;
    private final UnitFloorRepository unitFloorRepository;

    public UnitFloorServiceImpl(UnitFloorToUnitFloorCommand unitFloorToUnitFloorCommand,
                                UnitFloorCommandToUnitFloor unitFloorCommandToUnitFloor,
                                UnitFloorRepository unitFloorRepository)
    {
        this.unitFloorToUnitFloorCommand = unitFloorToUnitFloorCommand;
        this.unitFloorCommandToUnitFloor = unitFloorCommandToUnitFloor;
        this.unitFloorRepository = unitFloorRepository;
    }

    @Override
    @Transactional
    public Set<UnitFloorCommand> getUnitFloorCommands() {
        return StreamSupport.stream(unitFloorRepository.findAll()
                        .spliterator(), false)
                .map(unitFloorToUnitFloorCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitFloor findById(Long l) {
        Optional<UnitFloor> unitFloorOptional = unitFloorRepository.findById(l);

        if (!unitFloorOptional.isPresent()) {
            throw new RuntimeException("Unit Floor Not Found!");
        }

        return unitFloorOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        unitFloorRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public UnitFloorCommand saveUnitFloorCommand(UnitFloorCommand command) {

        UnitFloor detachedUnitFloor = unitFloorCommandToUnitFloor.convert(command);
        UnitFloor savedUnitFloor = unitFloorRepository.save(detachedUnitFloor);
        log.debug("Saved Unit Floor Id:" + savedUnitFloor.getUnitFloorCode());
        return unitFloorToUnitFloorCommand.convert(savedUnitFloor);

    }

    @Override
    public UnitFloor updateUnitFloor(UnitFloor unitFloor, Long l) {
        return unitFloorRepository.findById(l).map(unitFloor1 -> {
            unitFloor1.setUnitFloorCode(unitFloor.getUnitFloorCode());
            unitFloor1.setuFloorId(unitFloor.getuFloorId());
            unitFloor1.setuFloorDescr(unitFloor.getuFloorDescr());
            return unitFloorRepository.save(unitFloor);
        }).orElseGet(() -> {
            unitFloor.setUnitFloorCode(l);
            return unitFloorRepository.save(unitFloor);
        });
    }

    @Override
    @Transactional
    public UnitFloorCommand findUnitFloorCommandById(Long l) {
        return unitFloorToUnitFloorCommand.convert(findById(l));
    }
}
