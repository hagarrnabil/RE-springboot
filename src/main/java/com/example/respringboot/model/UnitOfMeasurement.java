package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
//@Data
@Table(name = "unit_of_measurement")
public class UnitOfMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long measurementCode;


    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String uomID;
    @NotNull
    private String uomDescr;

    @OneToOne
    private AreaMasterDetail areaMasterDetail;

    public void setArea(AreaMasterDetail area) {
        if (area != null) {
            this.areaMasterDetail = area;
            area.setUnitOfMeasurement(this);
        }
    }

    public Long getMeasurementCode() {
        return measurementCode;
    }

    public void setMeasurementCode(Long measurementCode) {
        this.measurementCode = measurementCode;
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

    public AreaMasterDetail getAreaMasterDetail() {
        return areaMasterDetail;
    }

    public void setAreaMasterDetail(AreaMasterDetail areaMasterDetail) {
        this.areaMasterDetail = areaMasterDetail;
    }
}
