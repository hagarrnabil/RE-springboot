package com.example.respringboot.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
//@EqualsAndHashCode(exclude = {"company","profitCenter","buildings"})
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectCode;

    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String projectId;
    @NotNull
    private String projectDescription;
    private LocalDate validFrom;
    private String profit;

    @ManyToOne
    private Company company;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Building> buildings = new HashSet<>();

    @ManyToOne
    private ProfitCenter profitCenter;

    @OneToOne(fetch = FetchType.EAGER)
    private Location location;

    public Project() {
    }

    public Project(String projectId, String projectDescription, LocalDate validFrom, String profit, Company company) {
        this.projectId = projectId;
        this.projectDescription = projectDescription;
        this.validFrom = validFrom;
        this.profit = profit;
        this.company = company;
    }

    public Project(String projectId, String projectDescription, LocalDate validFrom, String profit, Company company, Set<Building> buildings, ProfitCenter profitCenter, Location location) {
        this.projectId = projectId;
        this.projectDescription = projectDescription;
        this.validFrom = validFrom;
        this.profit = profit;
        this.company = company;
        this.buildings = buildings;
        this.profitCenter = profitCenter;
        this.location = location;
    }

    public Project addBuilding(Building building) {
        building.setProject(this);
        this.buildings.add(building);
        return this;
    }

//    public void setLocation(Location location) {
//        if (location != null) {
//            this.location = location;
//            location.setProject(this);
//        }
//    }
}
