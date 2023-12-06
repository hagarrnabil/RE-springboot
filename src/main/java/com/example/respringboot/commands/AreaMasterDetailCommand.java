package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AreaMasterDetailCommand {
    private Long id;
    private String areaMaster;
    private String description;
    private String projectFlag;
    private String buildingFlag;
    private String unitFlag;
    private UnitOfMeasurementCommand unitOfMeasurementCommand;
    private ProjectAreaCommand projectAreaCommand;
    private BuildingAreaCommand buildingAreaCommand;
    private UnitAreaCommand unitAreaCommand;
}
