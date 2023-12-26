package com.example.respringboot.services;

import com.example.respringboot.commands.AreaMasterDetailCommand;
import com.example.respringboot.model.AreaMasterDetail;

import java.util.Set;

public interface AreaMasterDetailService {
    Set<AreaMasterDetailCommand> getAreaCommands();

    AreaMasterDetail findById(Long l);

    void deleteById(Long idToDelete);

    AreaMasterDetailCommand saveAreaCommand(AreaMasterDetailCommand command);
    AreaMasterDetail updateArea(AreaMasterDetail newArea, Long l);

    AreaMasterDetailCommand findAreaCommandById(Long l);
}
