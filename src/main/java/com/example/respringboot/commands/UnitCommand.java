package com.example.respringboot.commands;

import com.example.respringboot.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
public class UnitCommand {
    private Long id;
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

}
