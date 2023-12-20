package com.example.respringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unit_fixture")
public class UnitFixture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitFixtureCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uFixtureId;
    @NotNull
    private String uFixtureDescr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitFixture")
    @JsonIgnore
    private Set<Unit> units = new HashSet<>();

    public UnitFixture addUnit(Unit unit) {
        unit.setUnitFixture(this);
        this.units.add(unit);
        return this;
    }

    public UnitFixture() {
    }

    public UnitFixture(Long unitFixtureCode, String uFixtureId, String uFixtureDescr, Set<Unit> units) {
        this.unitFixtureCode = unitFixtureCode;
        this.uFixtureId = uFixtureId;
        this.uFixtureDescr = uFixtureDescr;
        this.units = units;
    }

    public Long getUnitFixtureCode() {
        return unitFixtureCode;
    }

    public void setUnitFixtureCode(Long unitFixtureCode) {
        this.unitFixtureCode = unitFixtureCode;
    }

    public String getuFixtureId() {
        return uFixtureId;
    }

    public void setuFixtureId(String uFixtureId) {
        this.uFixtureId = uFixtureId;
    }

    public String getuFixtureDescr() {
        return uFixtureDescr;
    }

    public void setuFixtureDescr(String uFixtureDescr) {
        this.uFixtureDescr = uFixtureDescr;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }
}
