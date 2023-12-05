package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UsageTypeCommand {
    private Long id;
    private String usageId;
    private String usageDescr;
    private Set<UnitCommand> unitCommands = new HashSet<>();
}
