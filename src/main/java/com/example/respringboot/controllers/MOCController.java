package com.example.respringboot.controllers;

import com.example.respringboot.repositories.MoCRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MOCController {
    private MoCRepository moCRepository;

    public MOCController(MoCRepository moCRepository) {
        this.moCRepository = moCRepository;
    }

    @RequestMapping("/moc")
    public String getMoc(Model model) {

        model.addAttribute("moc", moCRepository.findAll());

        return "moc";

    }
}
