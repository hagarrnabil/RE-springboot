package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class UnitFixture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String uFixtureID;
    private String uFixtureDescr;

    public UnitFixture() {
    }

    public UnitFixture(Long id, String uFixtureID, String uFixtureDescr) {
        this.id = id;
        this.uFixtureID = uFixtureID;
        this.uFixtureDescr = uFixtureDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuFixtureID() {
        return uFixtureID;
    }

    public void setuFixtureID(String uFixtureID) {
        this.uFixtureID = uFixtureID;
    }

    public String getuFixtureDescr() {
        return uFixtureDescr;
    }

    public void setuFixtureDescr(String uFixtureDescr) {
        this.uFixtureDescr = uFixtureDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitFixture that = (UnitFixture) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UnitFixture{" +
                "id=" + id +
                ", uFixtureID='" + uFixtureID + '\'' +
                ", uFixtureDescr='" + uFixtureDescr + '\'' +
                '}';
    }
}
