package com.example.respringboot.services;


import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.model.Project;

import java.util.Set;

public interface ProjectService {
    Set<ProjectCommand> getProjectCommands();

    Project findById(Long l);

    void deleteById(Long idToDelete);

    ProjectCommand saveProjectCommand(ProjectCommand command);


//    ProjectCommand updatePC( Long l);

    Project updateProject(Project newProject, Long l);

    ProjectCommand findProjectCommandById(Long l);
}
