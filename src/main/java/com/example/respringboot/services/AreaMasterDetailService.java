package com.example.respringboot.services;

import com.example.respringboot.commands.AreaMasterDetailCommand;
import com.example.respringboot.model.AreaMasterDetail;
import com.example.respringboot.model.Company;

import java.util.Set;

public interface AreaMasterDetailService {
    Set<AreaMasterDetailCommand> getAreaCommands();

    AreaMasterDetail findById(Long l);

    void deleteById(Long idToDelete);

    AreaMasterDetailCommand saveAreaCommand(AreaMasterDetailCommand command);
    AreaMasterDetail updateArea(AreaMasterDetail area, Long l);

    AreaMasterDetailCommand findAreaCommandById(Long l);
}
