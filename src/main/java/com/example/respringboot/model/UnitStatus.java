package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class UnitStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uStatusID;
    private String uStatusDescr;

    public UnitStatus() {
    }

    public UnitStatus(Long id, String uStatusID, String uStatusDescr) {
        this.id = id;
        this.uStatusID = uStatusID;
        this.uStatusDescr = uStatusDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuStatusID() {
        return uStatusID;
    }

    public void setuStatusID(String uStatusID) {
        this.uStatusID = uStatusID;
    }

    public String getuStatusDescr() {
        return uStatusDescr;
    }

    public void setuStatusDescr(String uStatusDescr) {
        this.uStatusDescr = uStatusDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitStatus that = (UnitStatus) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UnitStatus{" +
                "id=" + id +
                ", uStatusID='" + uStatusID + '\'' +
                ", uStatusDescr='" + uStatusDescr + '\'' +
                '}';
    }
}
