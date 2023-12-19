package com.example.respringboot.services;

import com.example.respringboot.commands.UnitFixtureCommand;
import com.example.respringboot.converters.UnitFixtureCommandToUnitFixture;
import com.example.respringboot.converters.UnitFixtureToUnitFixtureCommand;
import com.example.respringboot.model.UnitFixture;
import com.example.respringboot.repositories.UnitFixtureRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitFixtureServiceImpl implements UnitFixtureService {
    private final UnitFixtureToUnitFixtureCommand unitFixtureToUnitFixtureCommand;
    private final UnitFixtureCommandToUnitFixture unitFixtureCommandToUnitFixture;
    private final UnitFixtureRepository unitFixtureRepository;

    public UnitFixtureServiceImpl(UnitFixtureToUnitFixtureCommand unitFixtureToUnitFixtureCommand,
                                  UnitFixtureCommandToUnitFixture unitFixtureCommandToUnitFixture,
                                  UnitFixtureRepository unitFixtureRepository)
    {
        this.unitFixtureToUnitFixtureCommand = unitFixtureToUnitFixtureCommand;
        this.unitFixtureCommandToUnitFixture = unitFixtureCommandToUnitFixture;
        this.unitFixtureRepository = unitFixtureRepository;
    }

    @Override
    @Transactional
    public Set<UnitFixtureCommand> getUnitFixtureCommands() {
        return StreamSupport.stream(unitFixtureRepository.findAll()
                        .spliterator(), false)
                .map(unitFixtureToUnitFixtureCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitFixture findById(Long l) {
        Optional<UnitFixture> unitFixtureOptional = unitFixtureRepository.findById(l);

        if (!unitFixtureOptional.isPresent()) {
            throw new RuntimeException("Unit Fixture Not Found!");
        }

        return unitFixtureOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        unitFixtureRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public UnitFixtureCommand saveUnitFixtureCommand(UnitFixtureCommand command) {
        UnitFixture detachedUnitFixture = unitFixtureCommandToUnitFixture.convert(command);
        UnitFixture savedUnitFixture = unitFixtureRepository.save(detachedUnitFixture);
        log.debug("Saved Unit Fixture Id:" + savedUnitFixture.getUnitFixtureCode());
        return unitFixtureToUnitFixtureCommand.convert(savedUnitFixture);
    }

    @Override
    public UnitFixture updateUnitFixture(UnitFixture unitFixture, Long l) {
        return unitFixtureRepository.findById(l).map(unitFixture1 -> {
            if (unitFixture.getUnitFixtureCode() != null) unitFixture1.setUnitFixtureCode(unitFixture.getUnitFixtureCode());
            if (unitFixture.getuFixtureId() != null) unitFixture1.setuFixtureId(unitFixture.getuFixtureId());
            if (unitFixture.getuFixtureDescr() != null) unitFixture1.setuFixtureDescr(unitFixture.getuFixtureDescr());
            return unitFixtureRepository.save(unitFixture);
        }).orElseGet(() -> {
            unitFixture.setUnitFixtureCode(l);
            return unitFixtureRepository.save(unitFixture);
        });
    }


    @Override
    @Transactional
    public UnitFixtureCommand findUnitFixtureCommandById(Long l) {
        return unitFixtureToUnitFixtureCommand.convert(findById(l));
    }
}
