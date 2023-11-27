package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class UnitView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uViewID;
    private String uViewDescr;

    public UnitView() {
    }

    public UnitView(Long id, String uViewID, String uViewDescr) {
        this.id = id;
        this.uViewID = uViewID;
        this.uViewDescr = uViewDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuViewID() {
        return uViewID;
    }

    public void setuViewID(String uViewID) {
        this.uViewID = uViewID;
    }

    public String getuViewDescr() {
        return uViewDescr;
    }

    public void setuViewDescr(String uViewDescr) {
        this.uViewDescr = uViewDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitView unitView = (UnitView) o;
        return Objects.equals(id, unitView.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UnitView{" +
                "id=" + id +
                ", uViewID='" + uViewID + '\'' +
                ", uViewDescr='" + uViewDescr + '\'' +
                '}';
    }
}
