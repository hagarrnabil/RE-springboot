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
public class UnitViewCommand implements Serializable {
    private Long id;
    private String uViewId;
    private String uViewDescr;
    @JsonIgnore
    private Set<UnitCommand> unitCommands = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuViewId() {
        return uViewId;
    }

    public void setuViewId(String uViewId) {
        this.uViewId = uViewId;
    }

    public String getuViewDescr() {
        return uViewDescr;
    }

    public void setuViewDescr(String uViewDescr) {
        this.uViewDescr = uViewDescr;
    }

    public Set<UnitCommand> getUnitCommands() {
        return unitCommands;
    }

    public void setUnitCommands(Set<UnitCommand> unitCommands) {
        this.unitCommands = unitCommands;
    }
}