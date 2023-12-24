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
public class UnitFloorCommand implements Serializable {
    private Long id;
    private String uFloorId;
    private String uFloorDescr;
    @JsonIgnore
    private Set<UnitCommand> unitCommands = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuFloorId() {
        return uFloorId;
    }

    public void setuFloorId(String uFloorId) {
        this.uFloorId = uFloorId;
    }

    public String getuFloorDescr() {
        return uFloorDescr;
    }

    public void setuFloorDescr(String uFloorDescr) {
        this.uFloorDescr = uFloorDescr;
    }

    public Set<UnitCommand> getUnitCommands() {
        return unitCommands;
    }

    public void setUnitCommands(Set<UnitCommand> unitCommands) {
        this.unitCommands = unitCommands;
    }
}
