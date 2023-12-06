package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UsageTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsageTypeController {
    private UsageTypeRepository usageRepository;

    public UsageTypeController(UsageTypeRepository usageRepository) {
        this.usageRepository = usageRepository;
    }

    @RequestMapping("/usages")
    public String getUsages(Model model) {

        model.addAttribute("usages", usageRepository.findAll());

        return "usages";

    }

}
