package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class UnitFloor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uFloorID;
    @NotNull
    private String uFloorDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitFloor")
    private Set<Unit> units = new HashSet<>();
    public UnitFloor() {
    }

    public UnitFloor(Long id, String uFloorID, String uFloorDescr) {
        this.id = id;
        this.uFloorID = uFloorID;
        this.uFloorDescr = uFloorDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuFloorID() {
        return uFloorID;
    }

    public void setuFloorID(String uFloorID) {
        this.uFloorID = uFloorID;
    }

    public String getuFloorDescr() {
        return uFloorDescr;
    }

    public void setuFloorDescr(String uFloorDescr) {
        this.uFloorDescr = uFloorDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitFloor unitFloor = (UnitFloor) o;
        return Objects.equals(id, unitFloor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UnitFloor{" +
                "id=" + id +
                ", uFloorID='" + uFloorID + '\'' +
                ", uFloorDescr='" + uFloorDescr + '\'' +
                '}';
    }
}
