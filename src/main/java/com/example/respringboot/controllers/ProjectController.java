package com.example.respringboot.controllers;

import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.converters.ProjectToProjectCommand;
import com.example.respringboot.model.Project;
import com.example.respringboot.repositories.ProjectRepository;
import com.example.respringboot.services.ProjectService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
public class ProjectController {
    private final ProjectRepository projectRepository;
    @Autowired
    private final ProjectService projectService;
    private final ProjectToProjectCommand projectToProjectCommand;

    public ProjectController(ProjectRepository projectRepository, ProjectService projectService,
                             ProjectToProjectCommand projectToProjectCommand) {
        this.projectRepository = projectRepository;
        this.projectService = projectService;
        this.projectToProjectCommand = projectToProjectCommand;
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

//    @PutMapping
//    @RequestMapping("/projects/{projectCode}")
//    ProjectCommand updateProjectCommand(@RequestBody Project newProject, @PathVariable Long projectCode) {
//
////        projectService.findProjectCommandById(projectCode);
//        ProjectCommand savedCommand = projectService.updatePC(Optional.ofNullable(newProject), projectCode);
//        return savedCommand;
//    }

    @PutMapping
    @RequestMapping("/projects/{projectCode}")
    @Transactional
    ProjectCommand updateProjectCommand(@RequestBody ProjectCommand newProjectCommand, @PathVariable Long projectCode){

        ProjectCommand command = projectToProjectCommand.convert(projectService.updateProject(newProjectCommand,projectCode));
        return command;
    }
}