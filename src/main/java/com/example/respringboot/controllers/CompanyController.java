package com.example.respringboot.controllers;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.converters.CompanyToCompanyCommand;
import com.example.respringboot.repositories.CompanyRepository;
import com.example.respringboot.services.CompanyService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class CompanyController {
    CompanyRepository companyRepository;
    private final CompanyService companyService;
    private final CompanyToCompanyCommand companyToCompanyCommand;

    public CompanyController(CompanyRepository companyRepository, CompanyService companyService, CompanyToCompanyCommand companyToCompanyCommand) {
        this.companyRepository = companyRepository;
        this.companyService = companyService;
        this.companyToCompanyCommand = companyToCompanyCommand;
    }

    @GetMapping("/companies")
    List<CompanyCommand> all() {
        return companyService.getCompanies().stream().map(company -> companyToCompanyCommand.convert(company))
                .collect(Collectors.toList());
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