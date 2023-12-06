package com.example.respringboot.controllers;

import com.example.respringboot.repositories.ProfitCenterRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfitCenterController {
    private ProfitCenterRepository profitCenterRepository;

    public ProfitCenterController(ProfitCenterRepository profitCenterRepository) {
        this.profitCenterRepository = profitCenterRepository;
    }

    @RequestMapping("/profits")
    public String getProfits(Model model) {

        model.addAttribute("profits", profitCenterRepository.findAll());

        return "profits";

    }

}
