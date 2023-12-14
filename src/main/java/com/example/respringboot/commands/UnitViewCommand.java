package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UnitViewCommand {
    private Long id;
    private String uViewId;
    private String uViewDescr;
    private Set<UnitCommand> unitCommands = new HashSet<>();
}