package com.example.respringboot.controllers;

import com.example.respringboot.repositories.MethodOfCalculationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MethodOfCalculationController {
    private MethodOfCalculationRepository methodOfCalculationRepository;

    public MethodOfCalculationController(MethodOfCalculationRepository methodOfCalculationRepository) {
        this.methodOfCalculationRepository = methodOfCalculationRepository;
    }

    @RequestMapping("/moc")
    public String getMoc(Model model) {

        model.addAttribute("moc", methodOfCalculationRepository.findAll());

        return "moc";

    }

}
