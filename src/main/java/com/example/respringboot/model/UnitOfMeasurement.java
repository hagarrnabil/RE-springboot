package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class UnitOfMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uomID;
    private String uomDescr;

    public UnitOfMeasurement() {
    }

    public UnitOfMeasurement(Long id, String uomID, String uomDescr) {
        this.id = id;
        this.uomID = uomID;
        this.uomDescr = uomDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUomID() {
        return uomID;
    }

    public void setUomID(String uomID) {
        this.uomID = uomID;
    }

    public String getUomDescr() {
        return uomDescr;
    }

    public void setUomDescr(String uomDescr) {
        this.uomDescr = uomDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitOfMeasurement that = (UnitOfMeasurement) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UnitOfMeasurement{" +
                "id=" + id +
                ", uomID='" + uomID + '\'' +
                ", uomDescr='" + uomDescr + '\'' +
                '}';
    }
}
