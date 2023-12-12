package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Data
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String locationId;
    @NotNull
    private String regionalLocation;

    @OneToOne(cascade = CascadeType.ALL)
    private Project project;

    public void setProject(Project project) {
        if (project != null) {
            this.project = project;
            project.setLocation(this);
        }
    }

}
