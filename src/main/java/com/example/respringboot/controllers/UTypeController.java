package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UTypeController {
    private UTypeRepository uTypeRepository;

    public UTypeController(UTypeRepository uTypeRepository) {
        this.uTypeRepository = uTypeRepository;
    }

    @RequestMapping("/types")
    public String getTypes(Model model) {

        model.addAttribute("types", uTypeRepository.findAll());

        return "types";

    }
}
