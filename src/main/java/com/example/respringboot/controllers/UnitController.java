package com.example.respringboot.controllers;

import com.example.respringboot.commands.UnitCommand;
import com.example.respringboot.repositories.UnitRepository;
import com.example.respringboot.services.UnitService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
public class UnitController {
    UnitRepository unitRepository;
    private final UnitService unitService;

    public UnitController(UnitRepository unitRepository, UnitService unitService) {
        this.unitRepository = unitRepository;
        this.unitService = unitService;
    }

    @GetMapping("/units")
    Set<UnitCommand> all() {
        return unitService.getUnitCommands();
    }

    @GetMapping("/units/{unitCode}")
    public Optional<UnitCommand> findByIds(@PathVariable @NotNull Long unitCode) {

        return Optional.ofNullable(unitService.findUnitCommandById(unitCode));
    }

    @PostMapping("/units")
    UnitCommand newUnitCommand(@RequestBody UnitCommand newUnitCommand) {

        UnitCommand savedCommand = unitService.saveUnitCommand(newUnitCommand);
        return savedCommand;

    }

    @DeleteMapping("/units/{unitCode}")
    void deleteUnitCommand(@PathVariable Long unitCode) {
        unitService.deleteById(unitCode);
    }

    @PutMapping
    @RequestMapping("/units/{unitCode}")
    UnitCommand updateUnitCommand(@RequestBody UnitCommand newUnitCommand, @PathVariable Long unitCode) {

        unitService.findUnitCommandById(unitCode);
        UnitCommand savedCommand = unitService.saveUnitCommand(newUnitCommand);
        return savedCommand;
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/units/search")
//    @ResponseBody
//    public List<CompanyCommand> Search(@RequestParam String keyword) {
//
//        return companyRepository.search(keyword);
//    }
}