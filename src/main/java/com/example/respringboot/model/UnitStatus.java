package com.example.respringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "unit_status")
public class UnitStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitStatusCode;
    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uStatusId;
    @NotNull
    private String uStatusDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitStatus")
    @JsonIgnore
    private Set<Unit> units = new HashSet<>();

    public UnitStatus(Long unitStatusCode, String uStatusId, String uStatusDescr, Set<Unit> units) {
        this.unitStatusCode = unitStatusCode;
        this.uStatusId = uStatusId;
        this.uStatusDescr = uStatusDescr;
        this.units = units;
    }

    public UnitStatus() {
    }

    public UnitStatus addUnit(Unit unit) {
        unit.setUnitStatus(this);
        this.units.add(unit);
        return this;
    }

    public Long getUnitStatusCode() {
        return unitStatusCode;
    }

    public void setUnitStatusCode(Long unitStatusCode) {
        this.unitStatusCode = unitStatusCode;
    }

    public String getuStatusId() {
        return uStatusId;
    }

    public void setuStatusId(String uStatusId) {
        this.uStatusId = uStatusId;
    }

    public String getuStatusDescr() {
        return uStatusDescr;
    }

    public void setuStatusDescr(String uStatusDescr) {
        this.uStatusDescr = uStatusDescr;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }
}
