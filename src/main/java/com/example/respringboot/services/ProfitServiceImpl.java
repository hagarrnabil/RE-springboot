package com.example.respringboot.services;

import com.example.respringboot.commands.ProfitCenterCommand;
import com.example.respringboot.converters.ProfitCommandToProfit;
import com.example.respringboot.converters.ProfitToProfitCommand;
import com.example.respringboot.model.ProfitCenter;
import com.example.respringboot.repositories.ProfitCenterRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ProfitServiceImpl implements ProfitService{
    private final ProfitCommandToProfit profitCommandToProfit;
    private final ProfitToProfitCommand profitToProfitCommand;
    private final ProfitCenterRepository profitCenterRepository;

    public ProfitServiceImpl(ProfitCommandToProfit profitCommandToProfit, ProfitToProfitCommand profitToProfitCommand, ProfitCenterRepository profitCenterRepository) {
        this.profitCommandToProfit = profitCommandToProfit;
        this.profitToProfitCommand = profitToProfitCommand;
        this.profitCenterRepository = profitCenterRepository;
    }

    @Override
    @Transactional
    public Set<ProfitCenterCommand> getProfitCommands() {
        return StreamSupport.stream(profitCenterRepository.findAll()
                        .spliterator(), false)
                .map(profitToProfitCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public ProfitCenter findById(Long l) {
        Optional<ProfitCenter> profitCenterOptional = profitCenterRepository.findById(l);

        if (!profitCenterOptional.isPresent()) {
            throw new RuntimeException("Profit Center Not Found!");
        }

        return profitCenterOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        profitCenterRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public ProfitCenterCommand saveProfitCommand(ProfitCenterCommand command) {

        ProfitCenter detachedProfit = profitCommandToProfit.convert(command);
        ProfitCenter savedProfit = profitCenterRepository.save(detachedProfit);
        log.debug("Saved Profit Center Id:" + savedProfit.getProfitCode());
        return profitToProfitCommand.convert(savedProfit);

    }

    @Override
    @Transactional
    public ProfitCenter updateProfit(ProfitCenter profitCenter, Long l) {
        return profitCenterRepository.findById(l).map(profitCenter1 -> {
            profitCenter1.setProfitCode(profitCenter.getProfitCode());
            profitCenter1.setProfitId(profitCenter.getProfitId());
            profitCenter1.setProfitDescr(profitCenter.getProfitDescr());
            return profitCenterRepository.save(profitCenter);
        }).orElseGet(() -> {
            profitCenter.setProfitCode(l);
            return profitCenterRepository.save(profitCenter);
        });
    }

    @Override
    @Transactional
    public ProfitCenterCommand findProfitCommandById(Long l) {
        return profitToProfitCommand.convert(findById(l));
    }
}
