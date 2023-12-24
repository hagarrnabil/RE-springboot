package com.example.respringboot.services;

import com.example.respringboot.commands.UnitFixtureCommand;
import com.example.respringboot.model.UnitFixture;

import java.util.Set;

public interface UnitFixtureService {
    Set<UnitFixtureCommand> getUnitFixtureCommands();

    UnitFixture findById(Long l);

    void deleteById(Long idToDelete);

    UnitFixtureCommand saveUnitFixtureCommand(UnitFixtureCommand command);
    UnitFixture updateUnitFixture(UnitFixtureCommand newUnitFixtureCommand, Long l);

    UnitFixtureCommand findUnitFixtureCommandById(Long l);
}