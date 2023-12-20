package com.example.respringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"projects","buildings"})
@Table(name = "profit_center")
public class ProfitCenter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long profitCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String profitId;
    @NotNull
    private String profitDescr;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "profitCenter")
    @JsonIgnore
    private Set<Project> projects = new HashSet<>();
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "profitCenter")
    @JsonIgnore
    private Set<Building> buildings = new HashSet<>();

    public ProfitCenter addProject(Project project) {
        project.setProfitCenter(this);
        this.projects.add(project);
        return this;
    }

    public ProfitCenter addBuilding(Building building) {
        building.setProfitCenter(this);
        this.buildings.add(building);
        return this;
    }

}
