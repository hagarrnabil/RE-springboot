package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class BuildingType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String bTypeID;
    private String bTypeDescr;

    public BuildingType() {
    }

    public BuildingType(Long id, String bTypeID, String bTypeDescr) {
        this.id = id;
        this.bTypeID = bTypeID;
        this.bTypeDescr = bTypeDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getbTypeID() {
        return bTypeID;
    }

    public void setbTypeID(String bTypeID) {
        this.bTypeID = bTypeID;
    }

    public String getbTypeDescr() {
        return bTypeDescr;
    }

    public void setbTypeDescr(String bTypeDescr) {
        this.bTypeDescr = bTypeDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildingType that = (BuildingType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BuildingType{" +
                "id=" + id +
                ", bTypeID='" + bTypeID + '\'' +
                ", bTypeDescr='" + bTypeDescr + '\'' +
                '}';
    }
}
