package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UnitSubtypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class USubtypeController {
    private UnitSubtypeRepository subtypeRepository;

    public USubtypeController(UnitSubtypeRepository subtypeRepository) {
        this.subtypeRepository = subtypeRepository;
    }

    @RequestMapping("/types")
    public String getTypes(Model model) {

        model.addAttribute("types", subtypeRepository.findAll());

        return "types";

    }
}
