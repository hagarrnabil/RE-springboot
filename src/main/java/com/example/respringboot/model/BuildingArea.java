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
//@EqualsAndHashCode(exclude = {"areaMasterDetails"})
@Table(name = "building_area")
public class BuildingArea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long buildingAreaCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String buildingArea;
    @NotNull
    private String description;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "buildingArea")
    @JsonIgnore
    private Set<AreaMasterDetail> areaMasterDetails = new HashSet<>();

    public BuildingArea addAMD(AreaMasterDetail areaMasterDetail) {
        areaMasterDetail.setBuildingArea(this);
        this.areaMasterDetails.add(areaMasterDetail);
        return this;
    }

    public Long getBuildingAreaCode() {
        return buildingAreaCode;
    }

    public void setBuildingAreaCode(Long buildingAreaCode) {
        this.buildingAreaCode = buildingAreaCode;
    }

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AreaMasterDetail> getAreaMasterDetails() {
        return areaMasterDetails;
    }

    public void setAreaMasterDetails(Set<AreaMasterDetail> areaMasterDetails) {
        this.areaMasterDetails = areaMasterDetails;
    }
}
