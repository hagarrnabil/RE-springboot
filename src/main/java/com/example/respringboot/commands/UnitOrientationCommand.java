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
public class UnitOrientationCommand implements Serializable {
    private Long id;
    private String uOrientationId;
    private String uOrientationDescr;
    @JsonIgnore
    private Set<UnitCommand> unitCommands = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuOrientationId() {
        return uOrientationId;
    }

    public void setuOrientationId(String uOrientationId) {
        this.uOrientationId = uOrientationId;
    }

    public String getuOrientationDescr() {
        return uOrientationDescr;
    }

    public void setuOrientationDescr(String uOrientationDescr) {
        this.uOrientationDescr = uOrientationDescr;
    }

    public Set<UnitCommand> getUnitCommands() {
        return unitCommands;
    }

    public void setUnitCommands(Set<UnitCommand> unitCommands) {
        this.unitCommands = unitCommands;
    }
}
