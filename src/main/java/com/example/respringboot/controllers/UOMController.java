package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UOMRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UOMController {
    private UOMRepository uomRepository;

    public UOMController(UOMRepository uomRepository) {
        this.uomRepository = uomRepository;
    }

    @RequestMapping("/measurements")
    public String getMeasurements(Model model) {

        model.addAttribute("measurements", uomRepository.findAll());

        return "measurements";

    }
}
