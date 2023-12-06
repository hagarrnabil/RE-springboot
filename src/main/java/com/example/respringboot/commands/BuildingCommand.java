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
public class BuildingCommand {
    private Long id;
    private String buildingId;
    private String buildingDescription;
    private String oldNumber;
    private LocalDate validFrom;
    private Integer numberOfFloors;
    private String profit;
    private ProjectCommand projectCommand;
    private Set<UnitCommand> unitCommands = new HashSet<>();
    private BuildingTypeCommand buildingTypeCommand;
    private ProfitCenterCommand profitCenterCommand;

}
