package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UnitFloorCommand {
    private Long id;
    private String uFloorId;
    private String uFloorDescr;
    private Set<UnitCommand> unitCommands = new HashSet<>();
}
