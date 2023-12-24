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
public class UnitFixtureCommand implements Serializable {
    private Long id;
    private String uFixtureId;
    private String uFixtureDescr;
    @JsonIgnore
    private Set<UnitCommand> unitCommands = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuFixtureId() {
        return uFixtureId;
    }

    public void setuFixtureId(String uFixtureId) {
        this.uFixtureId = uFixtureId;
    }

    public String getuFixtureDescr() {
        return uFixtureDescr;
    }

    public void setuFixtureDescr(String uFixtureDescr) {
        this.uFixtureDescr = uFixtureDescr;
    }

    public Set<UnitCommand> getUnitCommands() {
        return unitCommands;
    }

    public void setUnitCommands(Set<UnitCommand> unitCommands) {
        this.unitCommands = unitCommands;
    }
}
