package com.example.respringboot.services;

import com.example.respringboot.commands.UnitAreaCommand;
import com.example.respringboot.converters.UnitAreaCommandToUnitArea;
import com.example.respringboot.converters.UnitAreaToUnitAreaCommand;
import com.example.respringboot.model.UnitArea;
import com.example.respringboot.repositories.UnitAreaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitAreaServiceImpl implements UnitAreaService{
    private final UnitAreaToUnitAreaCommand unitAreaToUnitAreaCommand;
    private final UnitAreaCommandToUnitArea unitAreaCommandToUnitArea;
    private final UnitAreaRepository unitAreaRepository;

    public UnitAreaServiceImpl(UnitAreaToUnitAreaCommand unitAreaToUnitAreaCommand,
                               UnitAreaCommandToUnitArea unitAreaCommandToUnitArea,
                               UnitAreaRepository unitAreaRepository)
    {
        this.unitAreaToUnitAreaCommand = unitAreaToUnitAreaCommand;
        this.unitAreaCommandToUnitArea = unitAreaCommandToUnitArea;
        this.unitAreaRepository = unitAreaRepository;
    }

    @Override
    @Transactional
    public Set<UnitAreaCommand> getUnitAreaCommands() {
        return StreamSupport.stream(unitAreaRepository.findAll()
                        .spliterator(), false)
                .map(unitAreaToUnitAreaCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitArea findById(Long l) {
        Optional<UnitArea> unitAreaOptional = unitAreaRepository.findById(l);

        if (!unitAreaOptional.isPresent()) {
            throw new RuntimeException("Unit Area Not Found!");
        }

        return unitAreaOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        unitAreaRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public UnitAreaCommand saveUnitAreaCommand(UnitAreaCommand command) {

        UnitArea detachedUnitArea = unitAreaCommandToUnitArea.convert(command);
        UnitArea savedUnitArea = unitAreaRepository.save(detachedUnitArea);
        log.debug("Saved Unit Area Id:" + savedUnitArea.getUnitAreaCode());
        return unitAreaToUnitAreaCommand.convert(savedUnitArea);

    }

    @Override
    public UnitArea updateUnitArea(UnitAreaCommand newUnitAreaCommand, Long l) {
        return unitAreaRepository.findById(l).map(oldUnitArea -> {
            if (newUnitAreaCommand.getUnitArea() != oldUnitArea.getUnitArea()) oldUnitArea.setUnitArea(newUnitAreaCommand.getUnitArea());
            if (newUnitAreaCommand.getDescription() != oldUnitArea.getDescription()) oldUnitArea.setDescription(newUnitAreaCommand.getDescription());
            return unitAreaRepository.save(oldUnitArea);
        }).orElseThrow(() -> new RuntimeException("Unit Area not found"));
    }

    @Override
    @Transactional
    public UnitAreaCommand findUnitAreaCommandById(Long l) {
        return unitAreaToUnitAreaCommand.convert(findById(l));
    }
}
