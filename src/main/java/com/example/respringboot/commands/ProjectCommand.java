package com.example.respringboot.commands;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class ProjectCommand implements Serializable {
    private Long id;
    private Long companyCode;
    private Long profitCode;
    private Long locationCode;
    private String projectId;
    private String projectDescription;
    private LocalDate validFrom;
    private String profit;
    @JsonIgnore
    private Set<BuildingCommand> buildingCommands = new HashSet<>();
    @JsonIgnore
    private LocationCommand location;
}
