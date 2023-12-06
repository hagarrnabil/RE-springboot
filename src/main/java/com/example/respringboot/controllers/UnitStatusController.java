package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UnitStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UnitStatusController {
    private UnitStatusRepository statusRepository;

    public UnitStatusController(UnitStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @RequestMapping("/statuses")
    public String getStatuses(Model model) {

        model.addAttribute("statuses", statusRepository.findAll());

        return "statuses";

    }

}
