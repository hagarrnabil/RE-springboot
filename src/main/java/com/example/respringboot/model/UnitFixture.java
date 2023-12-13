package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"units"})
@Table(name = "unit_fixture")
public class UnitFixture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitFixtureCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)")
    @Length(max = 8)
    private String uFixtureId;
//    @NotNull
    private String uFixtureDescr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitFixture")
    private Set<Unit> units = new HashSet<>();

    public UnitFixture addUnit(Unit unit) {
        unit.setUnitFixture(this);
        this.units.add(unit);
        return this;
    }

}
