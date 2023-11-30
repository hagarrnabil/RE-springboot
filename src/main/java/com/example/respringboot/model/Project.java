package com.example.respringboot.model;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String projectID;
    @NotNull
    private String projectDescription;
    private LocalDate validFrom;

    @ManyToOne
    private Company company;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Building> buildings = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Profit> profits = new HashSet<>();

    @OneToOne
    private Location location;

    public Project() {
    }

    public Project(Long id, String projectID, String projectDescription, LocalDate validFrom) {
        this.id = id;
        this.projectID = projectID;
        this.projectDescription = projectDescription;
        this.validFrom = validFrom;
    }

    public Project(Long id, String projectID, String projectDescription, LocalDate validFrom, Company company) {
        this.id = id;
        this.projectID = projectID;
        this.projectDescription = projectDescription;
        this.validFrom = validFrom;
        this.company = company;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectID='" + projectID + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", validFrom=" + validFrom +
                '}';
    }
}
