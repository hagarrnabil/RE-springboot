package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, length = 8, columnDefinition = "char(8)", nullable = false)
    @Length(max = 8)
    private String unitKey;
    @Column(length = 8, columnDefinition = "char(8)")
    @Length(max = 8)
    private String oldNumber;
    @NotNull
    private String description;
    private LocalDate blockingDate;
    private String blockingReason;
    private String salesPhase;
    private LocalDate constructionDate;
    private LocalDate unitDeliveryDate;
    private String area;
    private Integer areaValue;
    private Integer noOfRooms;
    private Integer price;
    private LocalDate validFrom;
    @ManyToOne
    private Building building;
    @ManyToOne
    private UsageType usageType;
    @ManyToOne
    private UnitOrientation unitOrientation;
    @ManyToOne
    private UnitFixture unitFixture;
    @ManyToOne
    private UnitStatus unitStatus;
    @ManyToOne
    private UnitView unitView;
    @ManyToOne
    private UnitType unitType;
    @ManyToOne
    private UnitFloor unitFloor;
    @ManyToOne
    private UnitAreaDetails unitAreaDetails;
    @ManyToOne
    private UnitPaymentDetails unitPaymentDetails;


    public Unit(Long id, String unitKey, String oldNumber, String description, LocalDate blockingDate, String blockingReason, String salesPhase, LocalDate constructionDate, LocalDate unitDeliveryDate, String area, Integer areaValue, Integer noOfRooms, Integer price, LocalDate validFrom) {
        this.id = id;
        this.unitKey = unitKey;
        this.oldNumber = oldNumber;
        this.description = description;
        this.blockingDate = blockingDate;
        this.blockingReason = blockingReason;
        this.salesPhase = salesPhase;
        this.constructionDate = constructionDate;
        this.unitDeliveryDate = unitDeliveryDate;
        this.area = area;
        this.areaValue = areaValue;
        this.noOfRooms = noOfRooms;
        this.price = price;
        this.validFrom = validFrom;
    }

    public Unit(Long id, String unitKey, String oldNumber, String description, LocalDate blockingDate, String blockingReason, String salesPhase, LocalDate constructionDate, LocalDate unitDeliveryDate, String area, Integer areaValue, Integer noOfRooms, Integer price, LocalDate validFrom, Building building) {
        this.id = id;
        this.unitKey = unitKey;
        this.oldNumber = oldNumber;
        this.description = description;
        this.blockingDate = blockingDate;
        this.blockingReason = blockingReason;
        this.salesPhase = salesPhase;
        this.constructionDate = constructionDate;
        this.unitDeliveryDate = unitDeliveryDate;
        this.area = area;
        this.areaValue = areaValue;
        this.noOfRooms = noOfRooms;
        this.price = price;
        this.validFrom = validFrom;
        this.building = building;
    }

    public Unit() {
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitKey() {
        return unitKey;
    }

    public void setUnitKey(String unitKey) {
        this.unitKey = unitKey;
    }

    public String getOldNumber() {
        return oldNumber;
    }

    public void setOldNumber(String oldNumber) {
        this.oldNumber = oldNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getBlockingDate() {
        return blockingDate;
    }

    public void setBlockingDate(LocalDate blockingDate) {
        this.blockingDate = blockingDate;
    }

    public String getBlockingReason() {
        return blockingReason;
    }

    public void setBlockingReason(String blockingReason) {
        this.blockingReason = blockingReason;
    }

    public String getSalesPhase() {
        return salesPhase;
    }

    public void setSalesPhase(String salesPhase) {
        this.salesPhase = salesPhase;
    }

    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(LocalDate constructionDate) {
        this.constructionDate = constructionDate;
    }

    public LocalDate getUnitDeliveryDate() {
        return unitDeliveryDate;
    }

    public void setUnitDeliveryDate(LocalDate unitDeliveryDate) {
        this.unitDeliveryDate = unitDeliveryDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAreaValue() {
        return areaValue;
    }

    public void setAreaValue(Integer areaValue) {
        this.areaValue = areaValue;
    }

    public Integer getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(Integer noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(id, unit.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", unitKey='" + unitKey + '\'' +
                ", oldNumber='" + oldNumber + '\'' +
                ", description='" + description + '\'' +
                ", blockingDate=" + blockingDate +
                ", blockingReason='" + blockingReason + '\'' +
                ", salesPhase='" + salesPhase + '\'' +
                ", constructionDate=" + constructionDate +
                ", unitDeliveryDate=" + unitDeliveryDate +
                ", area='" + area + '\'' +
                ", areaValue=" + areaValue +
                ", noOfRooms=" + noOfRooms +
                ", price=" + price +
                ", validFrom=" + validFrom +
                ", building=" + building +
                '}';
    }
}
