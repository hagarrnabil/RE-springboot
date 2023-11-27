package com.example.respringboot.controllers;

import com.example.respringboot.repositories.UViewRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UViewController {
    private UViewRepository uViewRepository;

    public UViewController(UViewRepository uViewRepository) {
        this.uViewRepository = uViewRepository;
    }
    @RequestMapping("/views")
    public String getViews(Model model) {

        model.addAttribute("views", uViewRepository.findAll());

        return "views";

    }
}
