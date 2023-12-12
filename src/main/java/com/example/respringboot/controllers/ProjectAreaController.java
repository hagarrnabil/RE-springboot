package com.example.respringboot.controllers;

import com.example.respringboot.commands.ProjectAreaCommand;
import com.example.respringboot.services.ProjectAreaService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class ProjectAreaController {
    private final ProjectAreaService projectAreaService;

    public ProjectAreaController(ProjectAreaService projectAreaService) {
        this.projectAreaService = projectAreaService;
    }

    @GetMapping("/projectareas")
    Set<ProjectAreaCommand> all() {
        return projectAreaService.getProjectAreaCommands();
    }

    @GetMapping("/projectareas/{projectAreaCode}")
    public Optional<ProjectAreaCommand> findByIds(@PathVariable @NotNull Long projectAreaCode) {

        return Optional.ofNullable(projectAreaService.findProjectAreaCommandById(projectAreaCode));
    }

    @PostMapping("/projectareas")
    ProjectAreaCommand newProjectAreaCommand(@RequestBody ProjectAreaCommand newProjectAreaCommand) {

        ProjectAreaCommand savedCommand = projectAreaService.saveProjectAreaCommand(newProjectAreaCommand);
        return savedCommand;

    }

    @DeleteMapping("/projectareas/{projectAreaCode}")
    void deleteProjectAreaCommand(@PathVariable Long projectAreaCode) {
        projectAreaService.deleteById(projectAreaCode);
    }

    @PutMapping
    @RequestMapping("/projectareas/{projectAreaCode}")
    ProjectAreaCommand updateProjectAreaCommand(@RequestBody ProjectAreaCommand newProjectAreaCommand, @PathVariable Long projectAreaCode) {

        projectAreaService.findProjectAreaCommandById(projectAreaCode);
        ProjectAreaCommand savedCommand = projectAreaService.saveProjectAreaCommand(newProjectAreaCommand);
        return savedCommand;
    }
}
