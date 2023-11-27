package com.example.respringboot.controllers;

import com.example.respringboot.repositories.PriceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PriceController {
    private PriceRepository priceRepository;

    public PriceController(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @RequestMapping("/prices")
    public String getPrices(Model model) {

        model.addAttribute("prices", priceRepository.findAll());

        return "prices";

    }
}
