package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
//@Data
//@EqualsAndHashCode(exclude = {"project"})
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String locationId;
    @NotNull
    private String regionalLocation;

    @OneToOne(cascade = CascadeType.MERGE)
    private Project project;
    public Location() {
    }

    public Location(String locationId, String regionalLocation) {
        this.locationId = locationId;
        this.regionalLocation = regionalLocation;
    }

    public void setProject(Project project) {
        if (project != null) {
            this.project = project;
            project.setLocation(this);
        }
    }

    public Long getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(Long locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getRegionalLocation() {
        return regionalLocation;
    }

    public void setRegionalLocation(String regionalLocation) {
        this.regionalLocation = regionalLocation;
    }

    public Project getProject() {
        return project;
    }
}
