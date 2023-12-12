package com.example.respringboot.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"projects"})
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long companyCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String companyCodeId;
    @NotNull
    private String companyCodeDescription;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Set<Project> projects = new HashSet<>();


    public Company addProject(Project project){
        project.setCompany(this);
        this.projects.add(project);
        return this;
    }

}
