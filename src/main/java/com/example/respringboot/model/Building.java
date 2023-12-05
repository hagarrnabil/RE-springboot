package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = {"units"})
@Table(name = "building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String buildingId;
    @NotNull
    private String buildingDescription;
    @Column(length = 8, columnDefinition = "char(8)")
    @Length(max = 8)
    private String oldNumber;
    private LocalDate validFrom;
    private Integer numberOfFloors;
    private String profit;

    @ManyToOne
    private Project project;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "building")
    private Set<Unit> units = new HashSet<>();
    @ManyToOne
    private BuildingType buildingType;
    @ManyToOne
    private ProfitCenter profitCenter;
}
