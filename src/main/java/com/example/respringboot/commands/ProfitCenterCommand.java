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
public class ProfitCenterCommand implements Serializable {
    private Long id;
    private String profitId;
    private String profitDescr;
//    @JsonIgnore
    private Set<ProjectCommand> projectCommands = new HashSet<>();
//    @JsonIgnore
    private Set<BuildingCommand> buildingCommands = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfitId() {
        return profitId;
    }

    public void setProfitId(String profitId) {
        this.profitId = profitId;
    }

    public String getProfitDescr() {
        return profitDescr;
    }

    public void setProfitDescr(String profitDescr) {
        this.profitDescr = profitDescr;
    }

    public Set<ProjectCommand> getProjectCommands() {
        return projectCommands;
    }

    public void setProjectCommands(Set<ProjectCommand> projectCommands) {
        this.projectCommands = projectCommands;
    }

    public Set<BuildingCommand> getBuildingCommands() {
        return buildingCommands;
    }

    public void setBuildingCommands(Set<BuildingCommand> buildingCommands) {
        this.buildingCommands = buildingCommands;
    }
}
