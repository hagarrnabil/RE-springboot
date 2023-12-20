package com.example.respringboot.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UnitFloorCommand implements Serializable {
    private Long id;
    private String uFloorId;
    private String uFloorDescr;
    @JsonIgnore
    private Set<UnitCommand> unitCommands = new HashSet<>();
}
