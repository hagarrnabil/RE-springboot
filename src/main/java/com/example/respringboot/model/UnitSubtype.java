package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "unit_subtypes")
public class UnitSubtype {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitSubtypeCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uSubtypeId;
    @NotNull
    private String uSubtypeDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitSubtype")
    private Set<Unit> units = new HashSet<>();

    public UnitSubtype() {
    }

    public UnitSubtype(Long unitSubtypeCode, String uSubtypeId, String uSubtypeDescr, Set<Unit> units) {
        this.unitSubtypeCode = unitSubtypeCode;
        this.uSubtypeId = uSubtypeId;
        this.uSubtypeDescr = uSubtypeDescr;
        this.units = units;
    }

    public UnitSubtype addUnit(Unit unit) {
        unit.setUnitSubtype(this);
        this.units.add(unit);
        return this;
    }

    public Long getUnitSubtypeCode() {
        return unitSubtypeCode;
    }

    public void setUnitSubtypeCode(Long unitSubtypeCode) {
        this.unitSubtypeCode = unitSubtypeCode;
    }

    public String getuSubtypeId() {
        return uSubtypeId;
    }

    public void setuSubtypeId(String uSubtypeId) {
        this.uSubtypeId = uSubtypeId;
    }

    public String getuSubtypeDescr() {
        return uSubtypeDescr;
    }

    public void setuSubtypeDescr(String uSubtypeDescr) {
        this.uSubtypeDescr = uSubtypeDescr;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }
}
