package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class UnitStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uStatusID;
    @NotNull
    private String uStatusDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitStatus")
    private Set<Unit> units = new HashSet<>();

    public UnitStatus() {
    }

    public UnitStatus(Long id, String uStatusID, String uStatusDescr) {
        this.id = id;
        this.uStatusID = uStatusID;
        this.uStatusDescr = uStatusDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuStatusID() {
        return uStatusID;
    }

    public void setuStatusID(String uStatusID) {
        this.uStatusID = uStatusID;
    }

    public String getuStatusDescr() {
        return uStatusDescr;
    }

    public void setuStatusDescr(String uStatusDescr) {
        this.uStatusDescr = uStatusDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitStatus that = (UnitStatus) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UnitStatus{" +
                "id=" + id +
                ", uStatusID='" + uStatusID + '\'' +
                ", uStatusDescr='" + uStatusDescr + '\'' +
                '}';
    }
}
