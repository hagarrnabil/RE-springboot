package com.example.respringboot.controllers;

import com.example.respringboot.repositories.ProfitRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfitController {
    private ProfitRepository profitRepository;

    public ProfitController(ProfitRepository profitRepository) {
        this.profitRepository = profitRepository;
    }

    @RequestMapping("/profits")
    public String getProfits(Model model) {

        model.addAttribute("profits", profitRepository.findAll());

        return "profits";

    }
}
