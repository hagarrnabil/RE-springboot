package com.example.respringboot.commands;

import com.example.respringboot.model.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UnitStatusCommand {
    private Long id;
    private String uStatusId;
    private String uStatusDescr;
    private Set<UnitCommand> unitCommands = new HashSet<>();
}
