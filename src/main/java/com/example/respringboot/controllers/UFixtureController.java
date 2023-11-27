package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UFixtureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UFixtureController {
    private UFixtureRepository uFixtureRepository;

    public UFixtureController(UFixtureRepository uFixtureRepository) {
        this.uFixtureRepository = uFixtureRepository;
    }

    @RequestMapping("/fixtures")
    public String getFixtures(Model model) {

        model.addAttribute("fixtures", uFixtureRepository.findAll());

        return "fixtures";

    }
}
