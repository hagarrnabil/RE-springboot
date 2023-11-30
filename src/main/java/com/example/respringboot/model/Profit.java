package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
public class Profit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String profitID;
    @NotNull
    private String profitDescr;
    @ManyToOne
    private Project project;

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
