package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unit_orientation")
public class UnitOrientation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitOrientationCode;
    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uOrientationId;
    @NotNull
    private String uOrientationDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitOrientation")
    private Set<Unit> units = new HashSet<>();

    public UnitOrientation() {
    }

    public UnitOrientation(Long unitOrientationCode, String uOrientationId, String uOrientationDescr, Set<Unit> units) {
        this.unitOrientationCode = unitOrientationCode;
        this.uOrientationId = uOrientationId;
        this.uOrientationDescr = uOrientationDescr;
        this.units = units;
    }

    public UnitOrientation addUnit(Unit unit) {
        unit.setUnitOrientation(this);
        this.units.add(unit);
        return this;
    }

    public Long getUnitOrientationCode() {
        return unitOrientationCode;
    }

    public void setUnitOrientationCode(Long unitOrientationCode) {
        this.unitOrientationCode = unitOrientationCode;
    }

    public String getuOrientationId() {
        return uOrientationId;
    }

    public void setuOrientationId(String uOrientationId) {
        this.uOrientationId = uOrientationId;
    }

    public String getuOrientationDescr() {
        return uOrientationDescr;
    }

    public void setuOrientationDescr(String uOrientationDescr) {
        this.uOrientationDescr = uOrientationDescr;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }
}
