package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class UnitOrientation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uOrientationID;
    @NotNull
    private String uOrientationDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitOrientation")
    private Set<Unit> units = new HashSet<>();

    public UnitOrientation() {
    }

    public UnitOrientation(Long id, String uOrientationID, String uOrientationDescr) {
        this.id = id;
        this.uOrientationID = uOrientationID;
        this.uOrientationDescr = uOrientationDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuOrientationID() {
        return uOrientationID;
    }

    public void setuOrientationID(String uOrientationID) {
        this.uOrientationID = uOrientationID;
    }

    public String getuOrientationDescr() {
        return uOrientationDescr;
    }

    public void setuOrientationDescr(String uOrientationDescr) {
        this.uOrientationDescr = uOrientationDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitOrientation that = (UnitOrientation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UnitOrientation{" +
                "id=" + id +
                ", uOrientationID='" + uOrientationID + '\'' +
                ", uOrientationDescr='" + uOrientationDescr + '\'' +
                '}';
    }
}
