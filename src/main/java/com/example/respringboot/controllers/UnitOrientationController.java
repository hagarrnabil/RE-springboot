package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UnitOrientationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnitOrientationController {
    private UnitOrientationRepository orientationRepository;

    public UnitOrientationController(UnitOrientationRepository orientationRepository) {
        this.orientationRepository = orientationRepository;
    }

    @RequestMapping("/orientations")
    public String getOrientations(Model model) {

        model.addAttribute("orientations", orientationRepository.findAll());

        return "orientations";

    }

}
