package com.example.respringboot.controllers;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.repositories.CompanyRepository;
import com.example.respringboot.services.CompanyService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;


@RestController
public class CompanyController {
    CompanyRepository companyRepository;
    private final CompanyService companyService;

    public CompanyController(CompanyRepository companyRepository, CompanyService companyService) {
        this.companyRepository = companyRepository;
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    Set<CompanyCommand> all() {
        return companyService.getCompanies();
    }

    @GetMapping("/companies/{id}")
    public Optional<CompanyCommand> findByIds(@PathVariable @NotNull Long id) {

        return Optional.ofNullable(companyService.findCompanyCommandById(id));
    }

    @PostMapping("/companies")
    CompanyCommand newCompanyCommand(@RequestBody CompanyCommand newCompanyCommand) {

        CompanyCommand savedCommand = companyService.saveCompanyCommand(newCompanyCommand);
        return savedCommand;

    }

    @DeleteMapping("/companies/{id}")
    void deleteCompanyCommand(@PathVariable Long id) {
        companyService.deleteById(id);
    }
//
//    @PutMapping
//    @RequestMapping("/companies/{id}")
//    CompanyCommand updateCompanyCommand(@RequestBody CompanyCommand newCompanyCommand, @PathVariable Long id) {
//
//        return companyRepository.findById(id).map(company -> {
//            company.setId(newCompanyCommand.getId());
//            company.setCompanyCodeId(newCompanyCommand.getCompanyCodeId());
//            company.setCompanyCodeDescription(newCompanyCommand.getCompanyCodeDescription());
//
//            return orientationRepository.save(newOrientation);
//        }).orElseGet(() -> {
//            newOrientation.setOrientation_code(orientation_code);
//            return orientationRepository.save(newOrientation);
//        });
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/orientation/search")
//    @ResponseBody
//    public List<Orientation> Search(@RequestParam String keyword) {
//
//        return orientationRepository.search(keyword);
//    }
}