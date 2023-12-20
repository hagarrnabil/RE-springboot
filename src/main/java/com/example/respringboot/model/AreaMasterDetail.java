package com.example.respringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"units","projectArea","buildingArea","unitArea"})
@Table(name = "area_master_detail")
public class AreaMasterDetail implements Serializable {
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

    @OneToOne
    private UnitOfMeasurement unitOfMeasurement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "areaMasterDetail")
    @JsonIgnore
    private Set<Unit> units = new HashSet<>();
    @ManyToOne
    private ProjectArea projectArea;
    @ManyToOne
    private BuildingArea buildingArea;
    @ManyToOne
    private UnitArea unitArea;

    public AreaMasterDetail() {
    }
    public AreaMasterDetail(String areaMaster, String description, String projectFlag, String buildingFlag, String unitFlag, UnitOfMeasurement unitOfMeasurement) {
        this.areaMaster = areaMaster;
        this.description = description;
        this.projectFlag = projectFlag;
        this.buildingFlag = buildingFlag;
        this.unitFlag = unitFlag;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public AreaMasterDetail addUnit(Unit unit) {
        unit.setAreaMasterDetail(this);
        this.units.add(unit);
        return this;
    }
}
