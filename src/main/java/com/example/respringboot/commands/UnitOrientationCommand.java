package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UnitOrientationCommand {
    private Long id;
    private String uOrientationId;
    private String uOrientationDescr;
    private Set<UnitCommand> unitCommands = new HashSet<>();
}
