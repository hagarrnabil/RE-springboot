package com.example.respringboot.controllers;

import com.example.respringboot.repositories.USubtypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class USubtypeController {
    private USubtypeRepository uTypeRepository;

    public USubtypeController(USubtypeRepository uTypeRepository) {
        this.uTypeRepository = uTypeRepository;
    }

    @RequestMapping("/types")
    public String getTypes(Model model) {

        model.addAttribute("types", uTypeRepository.findAll());

        return "types";

    }
}
