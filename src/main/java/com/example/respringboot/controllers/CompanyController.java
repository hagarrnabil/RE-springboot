package com.example.respringboot.controllers;

import com.example.respringboot.commands.BuildingCommand;
import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.converters.CompanyCommandToCompany;
import com.example.respringboot.converters.CompanyToCompanyCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.repositories.CompanyRepository;
import com.example.respringboot.services.CompanyService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class CompanyController {
    CompanyRepository companyRepository;
    private final CompanyToCompanyCommand companyToCompanyCommand;
    private final CompanyCommandToCompany companyCommandToCompany;
    private final CompanyService companyService;

    public CompanyController(CompanyRepository companyRepository, CompanyToCompanyCommand companyToCompanyCommand,
                             CompanyCommandToCompany companyCommandToCompany,
                             CompanyService companyService)
    {
        this.companyRepository = companyRepository;
        this.companyToCompanyCommand = companyToCompanyCommand;
        this.companyCommandToCompany = companyCommandToCompany;
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    Set<CompanyCommand> all() {
        return companyService.getCompanyCommands();
    }

    @GetMapping("/companies/{companyCode}")
    public Optional<CompanyCommand> findByIds(@PathVariable @NotNull Long companyCode) {

        return Optional.ofNullable(companyService.findCompanyCommandById(companyCode));
    }

    @PostMapping("/companies")
    CompanyCommand newCompanyCommand(@RequestBody CompanyCommand newCompanyCommand) {

        CompanyCommand savedCommand = companyService.saveCompanyCommand(newCompanyCommand);
        return savedCommand;

    }

    @DeleteMapping("/companies/{companyCode}")
    void deleteCompanyCommand(@PathVariable Long companyCode) {
        companyService.deleteById(companyCode);
    }

    @PutMapping
    @RequestMapping("/companies/{companyCode}")
    @Transactional
    CompanyCommand updateCompanyCommand(@RequestBody CompanyCommand newCompanyCommand, @PathVariable Long companyCode) {

//        Company companyRequest = companyCommandToCompany.convert(newCompanyCommand);
//        Company company = companyService.updateCompanyCommand(companyRequest, companyCode);
//        CompanyCommand companyResponse = companyToCompanyCommand.convert(company);
//        return companyResponse;
        companyService.findCompanyCommandById(companyCode);
        CompanyCommand savedCommand = companyService.saveCompanyCommand(newCompanyCommand);
        return savedCommand;
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/companies/search")
//    @ResponseBody
//    public List<CompanyCommand> Search(@RequestParam String keyword) {
//
//        return companyRepository.search(keyword);
//    }
}