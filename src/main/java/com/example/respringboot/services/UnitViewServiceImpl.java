package com.example.respringboot.services;

import com.example.respringboot.commands.UnitViewCommand;
import com.example.respringboot.converters.UnitViewCommandToUnitView;
import com.example.respringboot.converters.UnitViewToUnitViewCommand;
import com.example.respringboot.model.UnitView;
import com.example.respringboot.model.UsageType;
import com.example.respringboot.repositories.UnitViewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitViewServiceImpl implements UnitViewService{
    private final UnitViewToUnitViewCommand unitViewToUnitViewCommand;
    private final UnitViewCommandToUnitView unitViewCommandToUnitView;
    private final UnitViewRepository unitViewRepository;

    public UnitViewServiceImpl(UnitViewToUnitViewCommand unitViewToUnitViewCommand,
                               UnitViewCommandToUnitView unitViewCommandToUnitView,
                               UnitViewRepository unitViewRepository)
    {
        this.unitViewToUnitViewCommand = unitViewToUnitViewCommand;
        this.unitViewCommandToUnitView = unitViewCommandToUnitView;
        this.unitViewRepository = unitViewRepository;
    }

    @Override
    @Transactional
    public Set<UnitViewCommand> getUnitViewCommands() {
        return StreamSupport.stream(unitViewRepository.findAll()
                        .spliterator(), false)
                .map(unitViewToUnitViewCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public UnitView findById(Long l) {
        Optional<UnitView> unitViewOptional = unitViewRepository.findById(l);

        if (!unitViewOptional.isPresent()) {
            throw new RuntimeException("Unit View Not Found!");
        }

        return unitViewOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        unitViewRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public UnitViewCommand saveUnitViewCommand(UnitViewCommand command) {
        UnitView detachedUnitView = unitViewCommandToUnitView.convert(command);
        UnitView savedUnitView = unitViewRepository.save(detachedUnitView);
        log.debug("Saved Unit View Id:" + savedUnitView.getUnitViewCode());
        return unitViewToUnitViewCommand.convert(savedUnitView);
    }

    @Override
    public UnitView updateUnitView(UnitView unitView, Long l) {
        return unitViewRepository.findById(l).map(unitView1 -> {
            unitView1.setUnitViewCode(unitView.getUnitViewCode());
            unitView1.setuViewId(unitView.getuViewId());
            unitView1.setuViewDescr(unitView.getuViewDescr());
            return unitViewRepository.save(unitView);
        }).orElseGet(() -> {
            unitView.setUnitViewCode(l);
            return unitViewRepository.save(unitView);
        });    }

    @Override
    @Transactional
    public UnitViewCommand findUnitViewCommandById(Long l) {
        return unitViewToUnitViewCommand.convert(findById(l));
    }
}
