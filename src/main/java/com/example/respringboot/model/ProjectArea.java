package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "project_area")
public class ProjectArea {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectAreaCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String projectArea;
    @NotNull
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectArea")
    private Set<AreaMasterDetail> areaMasterDetails = new HashSet<>();

    public ProjectArea addAMD(AreaMasterDetail areaMasterDetail) {
        areaMasterDetail.setProjectArea(this);
        this.areaMasterDetails.add(areaMasterDetail);
        return this;
    }
}
