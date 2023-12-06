package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UnitViewRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnitViewController {
    private UnitViewRepository viewRepository;

    public UnitViewController(UnitViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    @RequestMapping("/views")
    public String getViews(Model model) {

        model.addAttribute("views", viewRepository.findAll());

        return "views";

    }

}
