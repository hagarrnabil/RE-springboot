package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BuildingAreaCommand {
    private Long id;
    private String buildingArea;
    private String description;
    private Set<AreaMasterDetailCommand> areaMasterDetailCommands = new HashSet<>();
}
