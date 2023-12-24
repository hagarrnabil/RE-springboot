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
public class UnitSubtypeCommand implements Serializable {
    private Long id;
    private String uSubtypeId;
    private String uSubtypeDescr;
    @JsonIgnore
    private Set<UnitCommand> unitCommands = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuSubtypeId() {
        return uSubtypeId;
    }

    public void setuSubtypeId(String uSubtypeId) {
        this.uSubtypeId = uSubtypeId;
    }

    public String getuSubtypeDescr() {
        return uSubtypeDescr;
    }

    public void setuSubtypeDescr(String uSubtypeDescr) {
        this.uSubtypeDescr = uSubtypeDescr;
    }

    public Set<UnitCommand> getUnitCommands() {
        return unitCommands;
    }

    public void setUnitCommands(Set<UnitCommand> unitCommands) {
        this.unitCommands = unitCommands;
    }
}
