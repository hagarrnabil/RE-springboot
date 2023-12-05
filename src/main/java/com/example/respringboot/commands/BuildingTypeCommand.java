package com.example.respringboot.commands;

import com.example.respringboot.model.Building;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class BuildingTypeCommand {
    private Long id;
    private String buildingTypeId;
    private String buildingTypeDescr;
    private Set<BuildingCommand> buildingCommands = new HashSet<>();

}
