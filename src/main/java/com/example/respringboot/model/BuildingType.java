package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"buildings"})
@Table(name = "building_types")
public class BuildingType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String buildingTypeI;
    @NotNull
    private String buildingTypeDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buildingType")
    private Set<Building> buildings = new HashSet<>();

}
