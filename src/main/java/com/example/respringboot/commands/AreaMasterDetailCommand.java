package com.example.respringboot.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

//@Getter
//@Setter
//@NoArgsConstructor
public class AreaMasterDetailCommand implements Serializable {
    private Long id;
    private Long projectAreaCode;
    private Long buildingAreaCode;
    private Long unitAreaCode;
    private Long measurementCode;
    private String areaMaster;
    private String description;
    private String projectFlag;
    private String buildingFlag;
    private String unitFlag;
    @JsonIgnore
    private UnitOfMeasurementCommand unitOfMeasurementCommand;
    @JsonIgnore
    private Set<UnitCommand> unitCommands = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectAreaCode() {
        return projectAreaCode;
    }

    public void setProjectAreaCode(Long projectAreaCode) {
        this.projectAreaCode = projectAreaCode;
    }

    public Long getBuildingAreaCode() {
        return buildingAreaCode;
    }

    public void setBuildingAreaCode(Long buildingAreaCode) {
        this.buildingAreaCode = buildingAreaCode;
    }

    public Long getUnitAreaCode() {
        return unitAreaCode;
    }

    public void setUnitAreaCode(Long unitAreaCode) {
        this.unitAreaCode = unitAreaCode;
    }

    public Long getMeasurementCode() {
        return measurementCode;
    }

    public void setMeasurementCode(Long measurementCode) {
        this.measurementCode = measurementCode;
    }

    public String getAreaMaster() {
        return areaMaster;
    }

    public void setAreaMaster(String areaMaster) {
        this.areaMaster = areaMaster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectFlag() {
        return projectFlag;
    }

    public void setProjectFlag(String projectFlag) {
        this.projectFlag = projectFlag;
    }

    public String getBuildingFlag() {
        return buildingFlag;
    }

    public void setBuildingFlag(String buildingFlag) {
        this.buildingFlag = buildingFlag;
    }

    public String getUnitFlag() {
        return unitFlag;
    }

    public void setUnitFlag(String unitFlag) {
        this.unitFlag = unitFlag;
    }

    public UnitOfMeasurementCommand getUnitOfMeasurementCommand() {
        return unitOfMeasurementCommand;
    }

    public void setUnitOfMeasurementCommand(UnitOfMeasurementCommand unitOfMeasurementCommand) {
        this.unitOfMeasurementCommand = unitOfMeasurementCommand;
    }

    public Set<UnitCommand> getUnitCommands() {
        return unitCommands;
    }

    public void setUnitCommands(Set<UnitCommand> unitCommands) {
        this.unitCommands = unitCommands;
    }
}
