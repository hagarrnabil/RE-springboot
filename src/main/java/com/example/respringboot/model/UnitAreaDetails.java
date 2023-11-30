package com.example.respringboot.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class UnitAreaDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String areaID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitAreaDetails")
    private Set<Unit> units = new HashSet<>();
}
