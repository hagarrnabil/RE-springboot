package com.example.respringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"projects"})
@Table(name = "company")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)")
    @Length(max = 8)
    private String companyCodeId;
//    @NotNull
    private String companyCodeDescription;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "company")
    @JsonIgnore
    private Set<Project> projects = new HashSet<>();


    public Company addProject(Project project){
        project.setCompany(this);
        this.projects.add(project);
        return this;
    }

}
