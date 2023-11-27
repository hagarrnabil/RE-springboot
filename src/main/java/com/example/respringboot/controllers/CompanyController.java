package com.example.respringboot.controllers;

import com.example.respringboot.repositories.CompanyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompanyController {
    private CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @RequestMapping("/companies")
    public String getCompanies(Model model) {

        model.addAttribute("companies", companyRepository.findAll());

        return "companies";
    }
}
