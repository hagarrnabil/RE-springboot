package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class PriceType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String priceID;
    private String priceDescr;

    public PriceType() {
    }

    public PriceType(Long id, String priceID, String priceDescr) {
        this.id = id;
        this.priceID = priceID;
        this.priceDescr = priceDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPriceID() {
        return priceID;
    }

    public void setPriceID(String priceID) {
        this.priceID = priceID;
    }

    public String getPriceDescr() {
        return priceDescr;
    }

    public void setPriceDescr(String priceDescr) {
        this.priceDescr = priceDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceType priceType = (PriceType) o;
        return Objects.equals(id, priceType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PriceType{" +
                "id=" + id +
                ", priceID='" + priceID + '\'' +
                ", priceDescr='" + priceDescr + '\'' +
                '}';
    }
}
