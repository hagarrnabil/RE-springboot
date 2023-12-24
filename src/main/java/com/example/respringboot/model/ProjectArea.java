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
@Table(name = "project_area")
public class ProjectArea implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectAreaCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String projectArea;
    @NotNull
    private String description;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "projectArea")
    @JsonIgnore
    private Set<AreaMasterDetail> areaMasterDetails = new HashSet<>();

    public ProjectArea addAMD(AreaMasterDetail areaMasterDetail) {
        areaMasterDetail.setProjectArea(this);
        this.areaMasterDetails.add(areaMasterDetail);
        return this;
    }

    public Long getProjectAreaCode() {
        return projectAreaCode;
    }

    public void setProjectAreaCode(Long projectAreaCode) {
        this.projectAreaCode = projectAreaCode;
    }

    public String getProjectArea() {
        return projectArea;
    }

    public void setProjectArea(String projectArea) {
        this.projectArea = projectArea;
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
