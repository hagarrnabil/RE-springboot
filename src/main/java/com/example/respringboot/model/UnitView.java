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
@Table(name = "unit_view")
public class UnitView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitViewCode;
    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uViewId;
    @NotNull
    private String uViewDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitView")
    private Set<Unit> units = new HashSet<>();

    public UnitView addUnit(Unit unit) {
        unit.setUnitView(this);
        this.units.add(unit);
        return this;
    }

}
