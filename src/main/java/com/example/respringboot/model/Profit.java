package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Profit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String profitID;
    private String profitDescr;

    public Profit() {
    }

    public Profit(Long id, String profitID, String profitDescr) {
        this.id = id;
        this.profitID = profitID;
        this.profitDescr = profitDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProfitID() {
        return profitID;
    }

    public void setProfitID(String profitID) {
        this.profitID = profitID;
    }

    public String getProfitDescr() {
        return profitDescr;
    }

    public void setProfitDescr(String profitDescr) {
        this.profitDescr = profitDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit = (Profit) o;
        return Objects.equals(id, profit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Profit{" +
                "id=" + id +
                ", profitID='" + profitID + '\'' +
                ", profitDescr='" + profitDescr + '\'' +
                '}';
    }
}
