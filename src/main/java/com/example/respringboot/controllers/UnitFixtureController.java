package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UnitFixtureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnitFixtureController {
    private UnitFixtureRepository unitFixtureRepository;

    public UnitFixtureController(UnitFixtureRepository unitFixtureRepository) {
        this.unitFixtureRepository = unitFixtureRepository;
    }

    @RequestMapping("/fixtures")
    public String getFixtures(Model model) {

        model.addAttribute("fixtures", unitFixtureRepository.findAll());

        return "fixtures";

    }

}
