package com.example.respringboot.services;

import com.example.respringboot.commands.UnitStatusCommand;
import com.example.respringboot.converters.UnitStatusCommandToUnitStatus;
import com.example.respringboot.converters.UnitStatusToUnitStatusCommand;
import com.example.respringboot.model.UnitStatus;
import com.example.respringboot.repositories.UnitStatusRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitStatusServiceImpl implements UnitStatusService{
    private final UnitStatusToUnitStatusCommand unitStatusToUnitStatusCommand;
    private final UnitStatusCommandToUnitStatus unitStatusCommandToUnitStatus;
    private final UnitStatusRepository unitStatusRepository;

    public UnitStatusServiceImpl(UnitStatusToUnitStatusCommand unitStatusToUnitStatusCommand,
                                 UnitStatusCommandToUnitStatus unitStatusCommandToUnitStatus,
                                 UnitStatusRepository unitStatusRepository)
    {
        this.unitStatusToUnitStatusCommand = unitStatusToUnitStatusCommand;
        this.unitStatusCommandToUnitStatus = unitStatusCommandToUnitStatus;
        this.unitStatusRepository = unitStatusRepository;
    }

    @Override
    @Transactional
    public Set<UnitStatusCommand> getUnitStatusCommands() {
        return StreamSupport.stream(unitStatusRepository.findAll()
                        .spliterator(), false)
                .map(unitStatusToUnitStatusCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitStatus findById(Long l) {
        Optional<UnitStatus> unitStatusOptional = unitStatusRepository.findById(l);

        if (!unitStatusOptional.isPresent()) {
            throw new RuntimeException("Unit Status Not Found!");
        }

        return unitStatusOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        unitStatusRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public UnitStatusCommand saveUnitStatusCommand(UnitStatusCommand command) {

        UnitStatus detachedUnitStatus = unitStatusCommandToUnitStatus.convert(command);
        UnitStatus savedUnitStatus = unitStatusRepository.save(detachedUnitStatus);
        log.debug("Saved Unit Status Id:" + savedUnitStatus.getUnitStatusCode());
        return unitStatusToUnitStatusCommand.convert(savedUnitStatus);

    }

    @Override
    public UnitStatus updateUnitStatus(UnitStatusCommand newUnitStatusCommand, Long l) {
        return unitStatusRepository.findById(l).map(oldUnitStatus ->{
            if (newUnitStatusCommand.getStatusId() != oldUnitStatus.getStatusId()) oldUnitStatus.setStatusId(newUnitStatusCommand.getStatusId());
            if (newUnitStatusCommand.getStatusDescr() != oldUnitStatus.getStatusDescr()) oldUnitStatus.setStatusDescr(newUnitStatusCommand.getStatusDescr());
            return unitStatusRepository.save(oldUnitStatus);
        }).orElseThrow(() -> new RuntimeException("Unit Status not found"));
    }

    @Override
    @Transactional
    public UnitStatusCommand findUnitStatusCommandById(Long l) {
        return unitStatusToUnitStatusCommand.convert(findById(l));
    }
}
