package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
public class UnitOfMeasurementCommand {
    private Long id;
    private String uomID;
    private String uomDescr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUomID() {
        return uomID;
    }

    public void setUomID(String uomID) {
        this.uomID = uomID;
    }

    public String getUomDescr() {
        return uomDescr;
    }

    public void setUomDescr(String uomDescr) {
        this.uomDescr = uomDescr;
    }
}
