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
public class BuildingTypeCommand implements Serializable {
    private Long id;
    private String buildingTypeId;
    private String buildingTypeDescr;
//    @JsonIgnore
    private Set<BuildingCommand> buildingCommands = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingTypeId() {
        return buildingTypeId;
    }

    public void setBuildingTypeId(String buildingTypeId) {
        this.buildingTypeId = buildingTypeId;
    }

    public String getBuildingTypeDescr() {
        return buildingTypeDescr;
    }

    public void setBuildingTypeDescr(String buildingTypeDescr) {
        this.buildingTypeDescr = buildingTypeDescr;
    }

    public Set<BuildingCommand> getBuildingCommands() {
        return buildingCommands;
    }

    public void setBuildingCommands(Set<BuildingCommand> buildingCommands) {
        this.buildingCommands = buildingCommands;
    }
}
