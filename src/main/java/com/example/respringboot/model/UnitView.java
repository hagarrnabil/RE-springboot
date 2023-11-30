package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class UnitView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uViewID;
    @NotNull
    private String uViewDescr;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unitView")
    private Set<Unit> units = new HashSet<>();

    public UnitView() {
    }

    public UnitView(Long id, String uViewID, String uViewDescr) {
        this.id = id;
        this.uViewID = uViewID;
        this.uViewDescr = uViewDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getuViewID() {
        return uViewID;
    }

    public void setuViewID(String uViewID) {
        this.uViewID = uViewID;
    }

    public String getuViewDescr() {
        return uViewDescr;
    }

    public void setuViewDescr(String uViewDescr) {
        this.uViewDescr = uViewDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnitView unitView = (UnitView) o;
        return Objects.equals(id, unitView.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UnitView{" +
                "id=" + id +
                ", uViewID='" + uViewID + '\'' +
                ", uViewDescr='" + uViewDescr + '\'' +
                '}';
    }
}
