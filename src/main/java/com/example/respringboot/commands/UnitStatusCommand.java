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
public class UnitStatusCommand implements Serializable {
    private Long id;
    private String uStatusId;
    private String uStatusDescr;
    @JsonIgnore
    private Set<UnitCommand> unitCommands = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuStatusId() {
        return uStatusId;
    }

    public void setuStatusId(String uStatusId) {
        this.uStatusId = uStatusId;
    }

    public String getuStatusDescr() {
        return uStatusDescr;
    }

    public void setuStatusDescr(String uStatusDescr) {
        this.uStatusDescr = uStatusDescr;
    }

    public Set<UnitCommand> getUnitCommands() {
        return unitCommands;
    }

    public void setUnitCommands(Set<UnitCommand> unitCommands) {
        this.unitCommands = unitCommands;
    }
}
