package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class ProjectCommand {
    private Long id;
    private String projectId;
    private String projectDescription;
    private LocalDate validFrom;
    private String profit;
    private CompanyCommand companyCommand;
    private Set<BuildingCommand> buildingCommands = new HashSet<>();
    private ProfitCenterCommand profitCenterCommand;
    private LocationCommand locationCommand;
}
