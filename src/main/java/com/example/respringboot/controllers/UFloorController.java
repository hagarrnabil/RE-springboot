package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UFloorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UFloorController {
    private UFloorRepository uFloorRepository;

    public UFloorController(UFloorRepository uFloorRepository) {
        this.uFloorRepository = uFloorRepository;
    }

    @RequestMapping("/floors")
    public String getFloors(Model model) {

        model.addAttribute("floors", uFloorRepository.findAll());

        return "floors";

    }
}
