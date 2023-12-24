package com.example.respringboot.commands;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
//@Getter
//@Setter
//@NoArgsConstructor
public class UnitCommand implements Serializable {
    private Long id;
    private Long buildingCode;
    private Long areaCode;
    private Long unitFixtureCode;
    private Long unitFloorCode;
    private Long unitOrientationCode;
    private Long unitStatusCode;
    private Long unitSubtypeCode;
    private Long unitViewCode;
    private Long usageTypeCode;
    private String unitKey;
    private String oldNumber;
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
//    private BuildingCommand buildingCommand;
//    private UsageTypeCommand usageTypeCommand;
//    private UnitOrientationCommand unitOrientationCommand;
//    private UnitFixtureCommand unitFixtureCommand;
//    private UnitStatusCommand unitStatusCommand;
//    private UnitViewCommand unitViewCommand;
//    private UnitSubtypeCommand unitSubtypeCommand;
//    private UnitFloorCommand unitFloorCommand;
//    private AreaMasterDetailCommand areaMasterDetailCommand;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(Long buildingCode) {
        this.buildingCode = buildingCode;
    }

    public Long getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(Long areaCode) {
        this.areaCode = areaCode;
    }

    public Long getUnitFixtureCode() {
        return unitFixtureCode;
    }

    public void setUnitFixtureCode(Long unitFixtureCode) {
        this.unitFixtureCode = unitFixtureCode;
    }

    public Long getUnitFloorCode() {
        return unitFloorCode;
    }

    public void setUnitFloorCode(Long unitFloorCode) {
        this.unitFloorCode = unitFloorCode;
    }

    public Long getUnitOrientationCode() {
        return unitOrientationCode;
    }

    public void setUnitOrientationCode(Long unitOrientationCode) {
        this.unitOrientationCode = unitOrientationCode;
    }

    public Long getUnitStatusCode() {
        return unitStatusCode;
    }

    public void setUnitStatusCode(Long unitStatusCode) {
        this.unitStatusCode = unitStatusCode;
    }

    public Long getUnitSubtypeCode() {
        return unitSubtypeCode;
    }

    public void setUnitSubtypeCode(Long unitSubtypeCode) {
        this.unitSubtypeCode = unitSubtypeCode;
    }

    public Long getUnitViewCode() {
        return unitViewCode;
    }

    public void setUnitViewCode(Long unitViewCode) {
        this.unitViewCode = unitViewCode;
    }

    public Long getUsageTypeCode() {
        return usageTypeCode;
    }

    public void setUsageTypeCode(Long usageTypeCode) {
        this.usageTypeCode = usageTypeCode;
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
}
