package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class UnitType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uTypeID;
    private String uTypeDescr;

    public UnitType() {
    }

    public UnitType(Long id, String uTypeID, String uTypeDescr) {
        this.id = id;
        this.uTypeID = uTypeID;
        this.uTypeDescr = uTypeDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuTypeID() {
        return uTypeID;
    }

    public void setuTypeID(String uTypeID) {
        this.uTypeID = uTypeID;
    }

    public String getuTypeDescr() {
        return uTypeDescr;
    }

    public void setuTypeDescr(String uTypeDescr) {
        this.uTypeDescr = uTypeDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitType unitType = (UnitType) o;
        return Objects.equals(id, unitType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UnitType{" +
                "id=" + id +
                ", uTypeID='" + uTypeID + '\'' +
                ", uTypeDescr='" + uTypeDescr + '\'' +
                '}';
    }
}
