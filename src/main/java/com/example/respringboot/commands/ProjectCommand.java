package com.example.respringboot.commands;

import com.example.respringboot.model.Company;
import com.example.respringboot.model.ProfitCenter;
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
public class ProjectCommand implements Serializable {
    private Long id;
    private Long companyCode;
    private Long profitCode;
    private Long locationCode;
    private String projectId;
    private String projectDescription;
    private LocalDate validFrom;
    private String profit;
    private CompanyCommand company;
    private ProfitCenterCommand profitCenter;
//    @JsonIgnore
    private Set<BuildingCommand> buildingCommands = new HashSet<>();
//    @JsonIgnore
    private LocationCommand location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(Long companyCode) {
        this.companyCode = companyCode;
    }

    public Long getProfitCode() {
        return profitCode;
    }

    public void setProfitCode(Long profitCode) {
        this.profitCode = profitCode;
    }

    public Long getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(Long locationCode) {
        this.locationCode = locationCode;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public Set<BuildingCommand> getBuildingCommands() {
        return buildingCommands;
    }

    public void setBuildingCommands(Set<BuildingCommand> buildingCommands) {
        this.buildingCommands = buildingCommands;
    }

    public LocationCommand getLocation() {
        return location;
    }

    public void setLocation(LocationCommand location) {
        this.location = location;
    }

    public CompanyCommand getCompany() {
        return company;
    }

    public void setCompany(CompanyCommand company) {
        this.company = company;
    }

    public ProfitCenterCommand getProfitCenter() {
        return profitCenter;
    }

    public void setProfitCenter(ProfitCenterCommand profitCenter) {
        this.profitCenter = profitCenter;
    }
}
