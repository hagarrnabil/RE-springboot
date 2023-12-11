package com.example.respringboot.converters;

import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.model.Project;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectCommandToProject implements Converter<ProjectCommand, Project> {
    private final BuildingCommandToBuilding buildingConverter;

    public ProjectCommandToProject(BuildingCommandToBuilding buildingConverter) {
        this.buildingConverter = buildingConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Project convert(ProjectCommand source) {

        if (source == null) {
            return null;
        }

        final Project project = new Project();
        project.setProjectCode(source.getId());
        project.setProjectId(source.getProjectId());
        project.setProjectDescription(source.getProjectDescription());
        project.setValidFrom(source.getValidFrom());
        project.setProfit(source.getProfit());
        if (source.getBuildingCommands() != null && source.getBuildingCommands().size() > 0) {
            source.getBuildingCommands()
                    .forEach(buildingCommand -> project.getBuildings().add(buildingConverter.convert(buildingCommand)));
        }
        return project;

    }
}
