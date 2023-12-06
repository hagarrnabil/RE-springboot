package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UnitOfMeasurementRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnitOfMeasurementController {
    private UnitOfMeasurementRepository measurementRepository;

    public UnitOfMeasurementController(UnitOfMeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @RequestMapping("/measurements")
    public String getMeasurements(Model model) {

        model.addAttribute("measurements", measurementRepository.findAll());

        return "measurements";

    }

}
