package com.example.respringboot.converters;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.Project;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectCommandToProject implements Converter<ProjectCommand, Project> {

    @Synchronized
    @Nullable
    @Override
    public Project convert(ProjectCommand source) {
        if (source == null) {
            return null;
        }

        final Project project = new Project();
        project.setId(source.getId());
        project.setProjectId(source.getProjectId());
        project.setProjectDescription(source.getProjectDescription());
        project.setValidFrom(source.getValidFrom());
        return project;
    }
}
