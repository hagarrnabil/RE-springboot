package com.example.respringboot.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
//@Getter
//@Setter
//@NoArgsConstructor
public class BuildingCommand implements Serializable {
    private Long id;
    private Long profitCode;
    private Long projectCode;
    private Long buildingTypeCode;
    private String buildingId;
    private String buildingDescription;
    private String oldNumber;
    private LocalDate validFrom;
    private Integer numberOfFloors;
    private String profit;
    private ProjectCommand projectCommand;
//    @JsonIgnore
    private Set<UnitCommand> unitCommands = new HashSet<>();
    private BuildingTypeCommand buildingTypeCommand;
    private ProfitCenterCommand profitCenterCommand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProfitCode() {
        return profitCode;
    }

    public void setProfitCode(Long profitCode) {
        this.profitCode = profitCode;
    }

    public Long getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(Long projectCode) {
        this.projectCode = projectCode;
    }

    public Long getBuildingTypeCode() {
        return buildingTypeCode;
    }

    public void setBuildingTypeCode(Long buildingTypeCode) {
        this.buildingTypeCode = buildingTypeCode;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingDescription() {
        return buildingDescription;
    }

    public void setBuildingDescription(String buildingDescription) {
        this.buildingDescription = buildingDescription;
    }

    public String getOldNumber() {
        return oldNumber;
    }

    public void setOldNumber(String oldNumber) {
        this.oldNumber = oldNumber;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(Integer numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public Set<UnitCommand> getUnitCommands() {
        return unitCommands;
    }

    public void setUnitCommands(Set<UnitCommand> unitCommands) {
        this.unitCommands = unitCommands;
    }
}
