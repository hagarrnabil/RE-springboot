package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currencyID;
    private String currencyDescr;

    public Currency() {
    }

    public Currency(Long id, String currencyID, String currencyDescr) {
        this.id = id;
        this.currencyID = currencyID;
        this.currencyDescr = currencyDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(String currencyID) {
        this.currencyID = currencyID;
    }

    public String getCurrencyDescr() {
        return currencyDescr;
    }

    public void setCurrencyDescr(String currencyDescr) {
        this.currencyDescr = currencyDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", currencyID='" + currencyID + '\'' +
                ", currencyDescr='" + currencyDescr + '\'' +
                '}';
    }
}
