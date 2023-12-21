package com.example.respringboot.services;


import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.model.Project;

import java.util.Optional;
import java.util.Set;

public interface ProjectService {
    Set<ProjectCommand> getProjectCommands();

    Project findById(Long l);

    void deleteById(Long idToDelete);

    ProjectCommand saveProjectCommand(ProjectCommand command);

    ProjectCommand updatePC(Optional<Project> project, Long l);

    Project updateProject(ProjectCommand newProjectCommand, Long l);

    ProjectCommand findProjectCommandById(Long l);
}
