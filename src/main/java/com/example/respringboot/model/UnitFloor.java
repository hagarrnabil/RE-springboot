package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
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

    public UnitFloor addUnit(Unit unit) {
        unit.setUnitFloor(this);
        this.units.add(unit);
        return this;
    }
}
