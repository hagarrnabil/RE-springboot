package com.example.respringboot.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocationCommand {
    private Long id;
    private String locationId;
    private String regionalLocation;
    private ProjectCommand projectCommand;
}
