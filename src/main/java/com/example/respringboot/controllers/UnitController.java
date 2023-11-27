package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UnitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnitController {
    private UnitRepository unitRepository;

    public UnitController(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    @RequestMapping("/units")
    public String getUnits(Model model) {

        model.addAttribute("units", unitRepository.findAll());

        return "units";
    }
}
