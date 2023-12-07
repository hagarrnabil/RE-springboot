package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class ProfitCenterCommand {
    private Long id;
    private String profitId;
    private String profitDescr;
    private Set<ProjectCommand> projectCommands = new HashSet<>();
    private Set<BuildingCommand> buildingCommands = new HashSet<>();
}
