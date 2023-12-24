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
//@Data
//@EqualsAndHashCode(exclude = {"units","projectArea","buildingArea","unitArea"})
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
    private boolean projectFlag;
    private boolean buildingFlag;
    private boolean unitFlag;

    @OneToOne
    private UnitOfMeasurement unitOfMeasurement;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "areaMasterDetail")
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

    public AreaMasterDetail(Long areaCode, String areaMaster, String description, boolean projectFlag, boolean buildingFlag, boolean unitFlag, UnitOfMeasurement unitOfMeasurement, Set<Unit> units, ProjectArea projectArea, BuildingArea buildingArea, UnitArea unitArea) {
        this.areaCode = areaCode;
        this.areaMaster = areaMaster;
        this.description = description;
        this.projectFlag = projectFlag;
        this.buildingFlag = buildingFlag;
        this.unitFlag = unitFlag;
        this.unitOfMeasurement = unitOfMeasurement;
        this.units = units;
        this.projectArea = projectArea;
        this.buildingArea = buildingArea;
        this.unitArea = unitArea;
    }

    public AreaMasterDetail addUnit(Unit unit) {
        unit.setAreaMasterDetail(this);
        this.units.add(unit);
        return this;
    }

    public Long getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Long areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaMaster() {
        return areaMaster;
    }

    public void setAreaMaster(String areaMaster) {
        this.areaMaster = areaMaster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public UnitOfMeasurement getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(UnitOfMeasurement unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }

    public ProjectArea getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(ProjectArea projectArea) {
        this.projectArea = projectArea;
    }

    public BuildingArea getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(BuildingArea buildingArea) {
        this.buildingArea = buildingArea;
    }

    public UnitArea getUnitArea() {
        return unitArea;
    }

    public void setUnitArea(UnitArea unitArea) {
        this.unitArea = unitArea;
    }

    public boolean isProjectFlag() {
        return projectFlag;
    }

    public void setProjectFlag(boolean projectFlag) {
        this.projectFlag = projectFlag;
    }

    public boolean isBuildingFlag() {
        return buildingFlag;
    }

    public void setBuildingFlag(boolean buildingFlag) {
        this.buildingFlag = buildingFlag;
    }

    public boolean isUnitFlag() {
        return unitFlag;
    }

    public void setUnitFlag(boolean unitFlag) {
        this.unitFlag = unitFlag;
    }
}
