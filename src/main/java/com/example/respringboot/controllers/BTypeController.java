package com.example.respringboot.controllers;

import com.example.respringboot.repositories.BTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BTypeController {
    private BTypeRepository bTypeRepository;

    public BTypeController(BTypeRepository bTypeRepository) {
        this.bTypeRepository = bTypeRepository;
    }

    @RequestMapping("/bTypes")
    public String getBTypes(Model model) {

        model.addAttribute("bTypes", bTypeRepository.findAll());

        return "bTypes";

    }
}

