package com.example.respringboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
@Table(name = "unit")
public class Unit {
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

}
