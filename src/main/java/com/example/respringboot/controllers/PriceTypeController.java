package com.example.respringboot.controllers;

import com.example.respringboot.repositories.PriceTypeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PriceTypeController {
    private PriceTypeRepository priceTypeRepository;

    public PriceTypeController(PriceTypeRepository priceTypeRepository) {
        this.priceTypeRepository = priceTypeRepository;
    }

    @RequestMapping("/prices")
    public String getPrices(Model model) {

        model.addAttribute("prices", priceTypeRepository.findAll());

        return "prices";

    }

}
