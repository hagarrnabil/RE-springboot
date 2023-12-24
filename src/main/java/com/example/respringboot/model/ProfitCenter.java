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
@Table(name = "profit_center")
public class ProfitCenter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long profitCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)")
    @Length(max = 8)
    private String profitId;
    @NotNull
    private String profitDescr;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "profitCenter")
//    @JsonIgnore
    private Set<Project> projects = new HashSet<>();
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "profitCenter")
//    @JsonIgnore
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

    public ProfitCenter() {
    }

    public ProfitCenter(String profitId, String profitDescr, Set<Project> projects, Set<Building> buildings) {
        this.profitId = profitId;
        this.profitDescr = profitDescr;
        this.projects = projects;
        this.buildings = buildings;
    }

    public Long getProfitCode() {
        return profitCode;
    }

    public void setProfitCode(Long profitCode) {
        this.profitCode = profitCode;
    }

    public String getProfitId() {
        return profitId;
    }

    public void setProfitId(String profitId) {
        this.profitId = profitId;
    }

    public String getProfitDescr() {
        return profitDescr;
    }

    public void setProfitDescr(String profitDescr) {
        this.profitDescr = profitDescr;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Set<Building> buildings) {
        this.buildings = buildings;
    }
}
