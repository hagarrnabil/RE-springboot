package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UStatusRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UStatusController {
    private UStatusRepository uStatusRepository;

    public UStatusController(UStatusRepository uStatusRepository) {
        this.uStatusRepository = uStatusRepository;
    }
    @RequestMapping("/statuses")
    public String getStatuses(Model model) {

        model.addAttribute("statuses", uStatusRepository.findAll());

        return "statuses";

    }
}
