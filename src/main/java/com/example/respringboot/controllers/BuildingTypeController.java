package com.example.respringboot.controllers;

import com.example.respringboot.repositories.BuildingTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuildingTypeController {

    private BuildingTypeRepository typeRepository;

    public BuildingTypeController(BuildingTypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @RequestMapping("/bTypes")
    public String getBTypes(Model model) {

        model.addAttribute("bTypes", typeRepository.findAll());

        return "bTypes";

    }

}

