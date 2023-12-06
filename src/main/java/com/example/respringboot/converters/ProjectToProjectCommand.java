package com.example.respringboot.converters;

import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.model.Project;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectToProjectCommand implements Converter<Project, ProjectCommand> {


    @Synchronized
    @Nullable
    @Override
    public ProjectCommand convert(Project source) {
        if (source == null) {
            return null;
        }

        final ProjectCommand projectCommand = new ProjectCommand();
        projectCommand.setId(source.getId());
        projectCommand.setProjectId(source.getProjectId());
        projectCommand.setProjectDescription(source.getProjectDescription());
        return projectCommand;

    }
}
