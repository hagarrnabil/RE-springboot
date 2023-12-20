package com.example.respringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "unit_view")
public class UnitView implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitViewCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uViewId;;
    @NotNull
    private String uViewDescr;;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitView")
    @JsonIgnore
    private Set<Unit> units = new HashSet<>();

    public UnitView() {
    }

    public UnitView(Long unitViewCode, String uViewId, String uViewDescr, Set<Unit> units) {
        this.unitViewCode = unitViewCode;
        this.uViewId = uViewId;
        this.uViewDescr = uViewDescr;
        this.units = units;
    }

    public UnitView addUnit(Unit unit) {
        unit.setUnitView(this);
        this.units.add(unit);
        return this;
    }

    public Long getUnitViewCode() {
        return unitViewCode;
    }

    public void setUnitViewCode(Long unitViewCode) {
        this.unitViewCode = unitViewCode;
    }

    public String getuViewId() {
        return uViewId;
    }

    public void setuViewId(String uViewId) {
        this.uViewId = uViewId;
    }

    public String getuViewDescr() {
        return uViewDescr;
    }

    public void setuViewDescr(String uViewDescr) {
        this.uViewDescr = uViewDescr;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }
}