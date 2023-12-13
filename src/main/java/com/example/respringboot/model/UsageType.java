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
@Table(name = "usage_type")
public class UsageType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long usageTypeCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String usageId;
    @NotNull
    private String usageDescr;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usageType")
    private Set<Unit> units = new HashSet<>();

    public UsageType addUnit(Unit unit) {
        unit.setUsageType(this);
        this.units.add(unit);
        return this;
    }

}
