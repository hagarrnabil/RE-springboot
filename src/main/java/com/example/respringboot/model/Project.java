package com.example.respringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//@Data
//@EqualsAndHashCode(exclude = {"company","profitCenter","buildings","location"})
@Entity
@Table(name = "project")
public class Project implements Serializable {
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

    @ManyToOne(cascade = CascadeType.MERGE)
    private Company company;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "project")
//    @JsonIgnore
    private Set<Building> buildings = new HashSet<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    private ProfitCenter profitCenter;

    @OneToOne(cascade = CascadeType.MERGE)
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

    public Long getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(Long projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public String getProfit() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit = profit;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Set<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(Set<Building> buildings) {
        this.buildings = buildings;
    }

    public ProfitCenter getProfitCenter() {
        return profitCenter;
    }

    public void setProfitCenter(ProfitCenter profitCenter) {
        this.profitCenter = profitCenter;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
