package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class CompanyCommand {

    private Long id;
    private String companyCodeId;
    private String companyCodeDescription;
    private Set<ProjectCommand> projectCommands = new HashSet<>();

}
