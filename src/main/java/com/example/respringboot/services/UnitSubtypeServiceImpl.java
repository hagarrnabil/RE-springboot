package com.example.respringboot.services;

import com.example.respringboot.commands.UnitSubtypeCommand;
import com.example.respringboot.converters.UnitSubtypeCommandToUnitSubtype;
import com.example.respringboot.converters.UnitSubtypeToUnitSubtypeCommand;
import com.example.respringboot.model.UnitSubtype;
import com.example.respringboot.repositories.UnitSubtypeRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitSubtypeServiceImpl implements UnitSubtypeService{
    private final UnitSubtypeToUnitSubtypeCommand unitSubtypeToUnitSubtypeCommand;
    private final UnitSubtypeCommandToUnitSubtype unitSubtypeCommandToUnitSubtype;
    private final UnitSubtypeRepository unitSubtypeRepository;

    public UnitSubtypeServiceImpl(UnitSubtypeToUnitSubtypeCommand unitSubtypeToUnitSubtypeCommand,
                                  UnitSubtypeCommandToUnitSubtype unitSubtypeCommandToUnitSubtype,
                                  UnitSubtypeRepository unitSubtypeRepository)
    {
        this.unitSubtypeToUnitSubtypeCommand = unitSubtypeToUnitSubtypeCommand;
        this.unitSubtypeCommandToUnitSubtype = unitSubtypeCommandToUnitSubtype;
        this.unitSubtypeRepository = unitSubtypeRepository;
    }

    @Override
    @Transactional
    public Set<UnitSubtypeCommand> getUnitSubtypeCommands() {
        return StreamSupport.stream(unitSubtypeRepository.findAll()
                        .spliterator(), false)
                .map(unitSubtypeToUnitSubtypeCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitSubtype findById(Long l) {
        Optional<UnitSubtype> unitSubtypeOptional = unitSubtypeRepository.findById(l);

        if (!unitSubtypeOptional.isPresent()) {
            throw new RuntimeException("Unit Subtype Not Found!");
        }

        return unitSubtypeOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        unitSubtypeRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public UnitSubtypeCommand saveUnitSubtypeCommand(UnitSubtypeCommand command) {

        UnitSubtype detachedUnitSubtype = unitSubtypeCommandToUnitSubtype.convert(command);
        UnitSubtype savedUnitSubtype = unitSubtypeRepository.save(detachedUnitSubtype);
        log.debug("Saved Unit Subtype Id:" + savedUnitSubtype.getUnitSubtypeCode());
        return unitSubtypeToUnitSubtypeCommand.convert(savedUnitSubtype);

    }

    @Override
    public UnitSubtype updateUnitSubtype(UnitSubtype unitSubtype, Long l) {
        return unitSubtypeRepository.findById(l).map(unitSubtype1 -> {
            if (unitSubtype.getUnitSubtypeCode() != null) unitSubtype1.setUnitSubtypeCode(unitSubtype.getUnitSubtypeCode());
            if (unitSubtype.getuSubtypeId() != null) unitSubtype1.setuSubtypeId(unitSubtype.getuSubtypeId());
            if (unitSubtype.getuSubtypeDescr() != null) unitSubtype1.setuSubtypeDescr(unitSubtype.getuSubtypeDescr());
            return unitSubtypeRepository.save(unitSubtype);
        }).orElseGet(() -> {
            unitSubtype.setUnitSubtypeCode(l);
            return unitSubtypeRepository.save(unitSubtype);
        });
    }

    @Override
    @Transactional
    public UnitSubtypeCommand findUnitSubtypeCommandById(Long l) {
        return unitSubtypeToUnitSubtypeCommand.convert(findById(l));
    }
}
