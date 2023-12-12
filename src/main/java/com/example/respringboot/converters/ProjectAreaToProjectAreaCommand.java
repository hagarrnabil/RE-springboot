package com.example.respringboot.converters;

import com.example.respringboot.commands.ProjectAreaCommand;
import com.example.respringboot.model.ProjectArea;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectAreaToProjectAreaCommand implements Converter<ProjectArea, ProjectAreaCommand> {
    private final AreaMasterDetailToAreaMasterDetailCommand areaConverter;

    public ProjectAreaToProjectAreaCommand(AreaMasterDetailToAreaMasterDetailCommand areaConverter) {
        this.areaConverter = areaConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public ProjectAreaCommand convert(ProjectArea source) {
        if (source == null) {
            return null;
        }

        final ProjectAreaCommand projectAreaCommand = new ProjectAreaCommand();
        projectAreaCommand.setId(source.getProjectAreaCode());
        projectAreaCommand.setProjectArea(source.getProjectArea());
        projectAreaCommand.setDescription(source.getDescription());
        if (source.getAreaMasterDetails() != null && source.getAreaMasterDetails().size() > 0){
            source.getAreaMasterDetails()
                    .forEach(areaMasterDetail -> projectAreaCommand.getAreaMasterDetailCommands().add(areaConverter.convert(areaMasterDetail)));
        }
        return projectAreaCommand;
    }
}
