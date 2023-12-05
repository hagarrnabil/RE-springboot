package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UnitFixtureCommand {
    private Long id;
    private String uFixtureId;
    private String uFixtureDescr;
    private Set<UnitCommand> unitCommands = new HashSet<>();
}
