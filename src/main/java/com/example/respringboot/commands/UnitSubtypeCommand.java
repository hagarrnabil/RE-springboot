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
public class UnitSubtypeCommand implements Serializable {
    private Long id;
    private String uSubtypeId;
    private String uSubtypeDescr;
    @JsonIgnore
    private Set<UnitCommand> unitCommands = new HashSet<>();
}
