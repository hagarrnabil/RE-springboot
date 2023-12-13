package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "area_master_detail")
public class AreaMasterDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long areaCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String areaMaster;
    @NotNull
    private String description;
    private String projectFlag;
    private String buildingFlag;
    private String unitFlag;

    @OneToOne(cascade = CascadeType.ALL)
    private UnitOfMeasurement unitOfMeasurement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaMasterDetail")
    private Set<Unit> units = new HashSet<>();
    @ManyToOne
    private ProjectArea projectArea;
    @ManyToOne
    private BuildingArea buildingArea;
    @ManyToOne
    private UnitArea unitArea;

    public void setUoM(UnitOfMeasurement unitOfMeasurement) {
        if (unitOfMeasurement != null) {
            this.unitOfMeasurement = unitOfMeasurement;
            unitOfMeasurement.setAreaMasterDetail(this);
        }
    }
    public AreaMasterDetail addUnit(Unit unit) {
        unit.setAreaMasterDetail(this);
        this.units.add(unit);
        return this;
    }
}
