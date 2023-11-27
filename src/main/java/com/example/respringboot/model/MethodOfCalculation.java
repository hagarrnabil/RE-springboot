package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class MethodOfCalculation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String mocID;
    private String mocDescr;

    public MethodOfCalculation() {
    }

    public MethodOfCalculation(Long id, String mocID, String mocDescr) {
        this.id = id;
        this.mocID = mocID;
        this.mocDescr = mocDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMocID() {
        return mocID;
    }

    public void setMocID(String mocID) {
        this.mocID = mocID;
    }

    public String getMocDescr() {
        return mocDescr;
    }

    public void setMocDescr(String mocDescr) {
        this.mocDescr = mocDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodOfCalculation that = (MethodOfCalculation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MethodOfCalculation{" +
                "id=" + id +
                ", mocID='" + mocID + '\'' +
                ", mocDescr='" + mocDescr + '\'' +
                '}';
    }
}
