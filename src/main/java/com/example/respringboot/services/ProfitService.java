package com.example.respringboot.services;

import com.example.respringboot.commands.ProfitCenterCommand;
import com.example.respringboot.model.ProfitCenter;

import java.util.Set;

public interface ProfitService {
    Set<ProfitCenterCommand> getProfitCommands();

    ProfitCenter findById(Long l);

    void deleteById(Long idToDelete);

    ProfitCenterCommand saveProfitCommand(ProfitCenterCommand command);
    ProfitCenter updateProfit(ProfitCenterCommand newProfitCommand, Long l);

    ProfitCenterCommand findProfitCommandById(Long l);
}
