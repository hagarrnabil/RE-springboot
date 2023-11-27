package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UsageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsageController {
    private UsageRepository usageRepository;

    public UsageController(UsageRepository usageRepository) {
        this.usageRepository = usageRepository;
    }
    @RequestMapping("/usages")
    public String getUsages(Model model) {

        model.addAttribute("usages", usageRepository.findAll());

        return "usages";

    }
}
