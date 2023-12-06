package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UnitFloorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnitFloorController {
    private UnitFloorRepository unitFloorRepository;

    public UnitFloorController(UnitFloorRepository unitFloorRepository) {
        this.unitFloorRepository = unitFloorRepository;
    }

    @RequestMapping("/floors")
    public String getFloors(Model model) {

        model.addAttribute("floors", unitFloorRepository.findAll());

        return "floors";

    }

}
