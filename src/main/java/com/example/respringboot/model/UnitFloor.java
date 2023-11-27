package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class UnitFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uFloorID;
    private String uFloorDescr;

    public UnitFloor() {
    }

    public UnitFloor(Long id, String uFloorID, String uFloorDescr) {
        this.id = id;
        this.uFloorID = uFloorID;
        this.uFloorDescr = uFloorDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuFloorID() {
        return uFloorID;
    }

    public void setuFloorID(String uFloorID) {
        this.uFloorID = uFloorID;
    }

    public String getuFloorDescr() {
        return uFloorDescr;
    }

    public void setuFloorDescr(String uFloorDescr) {
        this.uFloorDescr = uFloorDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitFloor unitFloor = (UnitFloor) o;
        return Objects.equals(id, unitFloor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UnitFloor{" +
                "id=" + id +
                ", uFloorID='" + uFloorID + '\'' +
                ", uFloorDescr='" + uFloorDescr + '\'' +
                '}';
    }
}
