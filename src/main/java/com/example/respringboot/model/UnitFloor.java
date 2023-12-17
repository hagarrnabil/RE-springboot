package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "unit_floor")
public class UnitFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitFloorCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uFloorId;
    @NotNull
    private String uFloorDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitFloor")
    private Set<Unit> units = new HashSet<>();

    public UnitFloor() {
    }

    public UnitFloor(Long unitFloorCode, String uFloorId, String uFloorDescr, Set<Unit> units) {
        this.unitFloorCode = unitFloorCode;
        this.uFloorId = uFloorId;
        this.uFloorDescr = uFloorDescr;
        this.units = units;
    }

    public UnitFloor addUnit(Unit unit) {
        unit.setUnitFloor(this);
        this.units.add(unit);
        return this;
    }

    public Long getUnitFloorCode() {
        return unitFloorCode;
    }

    public void setUnitFloorCode(Long unitFloorCode) {
        this.unitFloorCode = unitFloorCode;
    }

    public String getuFloorId() {
        return uFloorId;
    }

    public void setuFloorId(String uFloorId) {
        this.uFloorId = uFloorId;
    }

    public String getuFloorDescr() {
        return uFloorDescr;
    }

    public void setuFloorDescr(String uFloorDescr) {
        this.uFloorDescr = uFloorDescr;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }
}
