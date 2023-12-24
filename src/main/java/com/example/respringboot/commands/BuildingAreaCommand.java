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
public class BuildingAreaCommand implements Serializable {
    private Long id;
    private String buildingArea;
    private String description;
    @JsonIgnore
    private Set<AreaMasterDetailCommand> areaMasterDetailCommands = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AreaMasterDetailCommand> getAreaMasterDetailCommands() {
        return areaMasterDetailCommands;
    }

    public void setAreaMasterDetailCommands(Set<AreaMasterDetailCommand> areaMasterDetailCommands) {
        this.areaMasterDetailCommands = areaMasterDetailCommands;
    }
}
