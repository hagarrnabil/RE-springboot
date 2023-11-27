package com.example.respringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class UsageType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String usageID;
    private String usageDescr;

    public UsageType() {
    }

    public UsageType(Long id, String usageID, String usageDescr) {
        this.id = id;
        this.usageID = usageID;
        this.usageDescr = usageDescr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsageID() {
        return usageID;
    }

    public void setUsageID(String usageID) {
        this.usageID = usageID;
    }

    public String getUsageDescr() {
        return usageDescr;
    }

    public void setUsageDescr(String usageDescr) {
        this.usageDescr = usageDescr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsageType usageType = (UsageType) o;
        return Objects.equals(id, usageType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UsageType{" +
                "id=" + id +
                ", usageID='" + usageID + '\'' +
                ", usageDescr='" + usageDescr + '\'' +
                '}';
    }
}
