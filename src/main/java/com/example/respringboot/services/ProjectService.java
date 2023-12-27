package com.example.respringboot.services;


import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.model.Project;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

public interface ProjectService {
    Set<ProjectCommand> getProjectCommands();

    Project findById(Long l);

    void deleteById(Long idToDelete);

    ProjectCommand saveProjectCommand(ProjectCommand command);


    Project updateProject(Project newProject, Long l);


    ProjectCommand findProjectCommandById(Long l);
}
