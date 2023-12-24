package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
//@Data
//@EqualsAndHashCode(exclude = {"building","usageType","unitOrientation","unitFixture","unitStatus",
//        "unitView","unitSubtype","unitFloor","areaMasterDetail"})
@Table(name = "unit")
public class Unit implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unitCode;


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
    private Integer fromFloor;
    private Integer toFloor;

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
    private UnitSubtype unitSubtype;
    @ManyToOne
    private UnitFloor unitFloor;
    @ManyToOne
    private AreaMasterDetail areaMasterDetail;
    @ManyToOne
    private UnitPaymentDetails unitPaymentDetails;

    public Unit() {
    }

    public Unit(String unitKey, String oldNumber, String description, LocalDate blockingDate, String blockingReason, String salesPhase, LocalDate constructionDate, LocalDate unitDeliveryDate, String area, Integer areaValue,
                Integer noOfRooms, Integer price, LocalDate validFrom, Integer fromFloor, Integer toFloor, Building building) {
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
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
        this.building = building;
    }

    public Unit(String unitKey, String oldNumber, String description, LocalDate blockingDate, String blockingReason, String salesPhase, LocalDate constructionDate, LocalDate unitDeliveryDate, String area, Integer areaValue, Integer noOfRooms,
                Integer price, LocalDate validFrom, Integer fromFloor, Integer toFloor, Building building, UsageType usageType, UnitOrientation unitOrientation, UnitFixture unitFixture, UnitStatus unitStatus, UnitView unitView, UnitSubtype unitSubtype,
                UnitFloor unitFloor, AreaMasterDetail areaMasterDetail, UnitPaymentDetails unitPaymentDetails) {
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
        this.fromFloor = fromFloor;
        this.toFloor = toFloor;
        this.building = building;
        this.usageType = usageType;
        this.unitOrientation = unitOrientation;
        this.unitFixture = unitFixture;
        this.unitStatus = unitStatus;
        this.unitView = unitView;
        this.unitSubtype = unitSubtype;
        this.unitFloor = unitFloor;
        this.areaMasterDetail = areaMasterDetail;
        this.unitPaymentDetails = unitPaymentDetails;
    }

    public Long getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(Long unitCode) {
        this.unitCode = unitCode;
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

    public Integer getFromFloor() {
        return fromFloor;
    }

    public void setFromFloor(Integer fromFloor) {
        this.fromFloor = fromFloor;
    }

    public Integer getToFloor() {
        return toFloor;
    }

    public void setToFloor(Integer toFloor) {
        this.toFloor = toFloor;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    public UnitOrientation getUnitOrientation() {
        return unitOrientation;
    }

    public void setUnitOrientation(UnitOrientation unitOrientation) {
        this.unitOrientation = unitOrientation;
    }

    public UnitFixture getUnitFixture() {
        return unitFixture;
    }

    public void setUnitFixture(UnitFixture unitFixture) {
        this.unitFixture = unitFixture;
    }

    public UnitStatus getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(UnitStatus unitStatus) {
        this.unitStatus = unitStatus;
    }

    public UnitView getUnitView() {
        return unitView;
    }

    public void setUnitView(UnitView unitView) {
        this.unitView = unitView;
    }

    public UnitSubtype getUnitSubtype() {
        return unitSubtype;
    }

    public void setUnitSubtype(UnitSubtype unitSubtype) {
        this.unitSubtype = unitSubtype;
    }

    public UnitFloor getUnitFloor() {
        return unitFloor;
    }

    public void setUnitFloor(UnitFloor unitFloor) {
        this.unitFloor = unitFloor;
    }

    public AreaMasterDetail getAreaMasterDetail() {
        return areaMasterDetail;
    }

    public void setAreaMasterDetail(AreaMasterDetail areaMasterDetail) {
        this.areaMasterDetail = areaMasterDetail;
    }

    public UnitPaymentDetails getUnitPaymentDetails() {
        return unitPaymentDetails;
    }

    public void setUnitPaymentDetails(UnitPaymentDetails unitPaymentDetails) {
        this.unitPaymentDetails = unitPaymentDetails;
    }
}
