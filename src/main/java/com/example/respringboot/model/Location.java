package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String locationID;
    private String regionalLocation;

    public Location() {
    }

    public Location(Long id, String locationID, String regionalLocation) {
        this.id = id;
        this.locationID = locationID;
        this.regionalLocation = regionalLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getRegionalLocation() {
        return regionalLocation;
    }

    public void setRegionalLocation(String regionalLocation) {
        this.regionalLocation = regionalLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(id, location.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", locationID='" + locationID + '\'' +
                ", regionalLocation='" + regionalLocation + '\'' +
                '}';
    }
}
