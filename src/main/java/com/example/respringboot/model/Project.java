package com.example.respringboot.model;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
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
@EqualsAndHashCode(exclude = {"buildings"})
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String projectId;
    @NotNull
    private String projectDescription;
    private LocalDate validFrom;
    private String profit;

    @ManyToOne
    private Company company;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<Building> buildings = new HashSet<>();

    @ManyToOne
    private ProfitCenter profitCenter;

    @OneToOne
    private Location location;

}
