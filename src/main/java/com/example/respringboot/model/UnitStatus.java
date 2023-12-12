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
@Table(name = "unit_status")
public class UnitStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitStatusCode;
    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uStatusId;
    @NotNull
    private String uStatusDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitStatus")
    private Set<Unit> units = new HashSet<>();

    public UnitStatus addUnit(Unit unit) {
        unit.setUnitStatus(this);
        this.units.add(unit);
        return this;
    }

}
