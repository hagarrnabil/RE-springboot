package com.example.respringboot.services;

import com.example.respringboot.commands.ProjectAreaCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.ProjectArea;

import java.util.Set;

public interface ProjectAreaService {
    Set<ProjectAreaCommand> getProjectAreaCommands();

    ProjectArea findById(Long l);

    void deleteById(Long idToDelete);

    ProjectAreaCommand saveProjectAreaCommand(ProjectAreaCommand command);
    ProjectArea updateProjectArea(ProjectArea projectArea, Long l);

    ProjectAreaCommand findProjectAreaCommandById(Long l);
}
