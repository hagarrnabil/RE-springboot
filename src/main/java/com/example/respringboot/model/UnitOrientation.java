package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class UnitOrientation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uOrientationID;
    private String uOrientationDescr;

    public UnitOrientation() {
    }

    public UnitOrientation(Long id, String uOrientationID, String uOrientationDescr) {
        this.id = id;
        this.uOrientationID = uOrientationID;
        this.uOrientationDescr = uOrientationDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuOrientationID() {
        return uOrientationID;
    }

    public void setuOrientationID(String uOrientationID) {
        this.uOrientationID = uOrientationID;
    }

    public String getuOrientationDescr() {
        return uOrientationDescr;
    }

    public void setuOrientationDescr(String uOrientationDescr) {
        this.uOrientationDescr = uOrientationDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitOrientation that = (UnitOrientation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UnitOrientation{" +
                "id=" + id +
                ", uOrientationID='" + uOrientationID + '\'' +
                ", uOrientationDescr='" + uOrientationDescr + '\'' +
                '}';
    }
}
