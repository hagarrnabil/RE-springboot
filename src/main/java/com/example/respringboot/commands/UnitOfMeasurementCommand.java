package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UnitOfMeasurementCommand {
    private Long measurementCode;
    private String uomID;
    private String uomDescr;
}
