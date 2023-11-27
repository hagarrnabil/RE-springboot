package com.example.respringboot.controllers;

import com.example.respringboot.repositories.BuildingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuildingController {
    private BuildingRepository buildingRepository;

    public BuildingController(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @RequestMapping("/buildings")
    public String getBuildings(Model model) {

        model.addAttribute("buildings", buildingRepository.findAll());

        return "buildings";
    }
}
