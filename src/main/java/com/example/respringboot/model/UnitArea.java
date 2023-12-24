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
@Table(name = "unit_area")
public class UnitArea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitAreaCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String unitArea;
    @NotNull
    private String description;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "unitArea")
    @JsonIgnore
    private Set<AreaMasterDetail> areaMasterDetails = new HashSet<>();

    public UnitArea addAMD(AreaMasterDetail areaMasterDetail) {
        areaMasterDetail.setUnitArea(this);
        this.areaMasterDetails.add(areaMasterDetail);
        return this;
    }

    public Long getUnitAreaCode() {
        return unitAreaCode;
    }

    public void setUnitAreaCode(Long unitAreaCode) {
        this.unitAreaCode = unitAreaCode;
    }

    public String getUnitArea() {
        return unitArea;
    }

    public void setUnitArea(String unitArea) {
        this.unitArea = unitArea;
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
