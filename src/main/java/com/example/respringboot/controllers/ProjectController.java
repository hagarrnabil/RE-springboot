package com.example.respringboot.controllers;

import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.repositories.ProjectRepository;
import com.example.respringboot.services.ProjectService;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class ProjectController {
    ProjectRepository projectRepository;
    private final ProjectService projectService;

    public ProjectController(ProjectRepository projectRepository, ProjectService projectService) {
        this.projectRepository = projectRepository;
        this.projectService = projectService;
    }

    @GetMapping("/projects")
    Set<ProjectCommand> all() {
        return projectService.getProjectCommands();
    }

    @GetMapping("/projects/{projectCode}")
    public Optional<ProjectCommand> findByIds(@PathVariable @NotNull Long projectCode) {

        return Optional.ofNullable(projectService.findProjectCommandById(projectCode));

    }

    @PostMapping("/projects")
    ProjectCommand newProjectCommand(@RequestBody ProjectCommand newProjectCommand) {

        ProjectCommand savedCommand = projectService.saveProjectCommand(newProjectCommand);
        return savedCommand;

    }

    @DeleteMapping("/projects/{projectCode}")
    void deleteProjectCommand(@PathVariable Long projectCode) {
        projectService.deleteById(projectCode);
    }

    @PutMapping
    @RequestMapping("/projects/{projectCode}")
    ProjectCommand updateProjectCommand(@RequestBody ProjectCommand newProjectCommand, @PathVariable Long projectCode) {

        projectService.findProjectCommandById(projectCode);
        ProjectCommand savedCommand = projectService.saveProjectCommand(newProjectCommand);
        return savedCommand;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/projects/search")
    @ResponseBody
    public List<ProjectCommand> Search(@RequestParam String keyword) {

        return projectRepository.search(keyword);
    }
}