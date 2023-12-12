package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
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

    public UnitOrientation addUnit(Unit unit) {
        unit.setUnitOrientation(this);
        this.units.add(unit);
        return this;
    }

}
