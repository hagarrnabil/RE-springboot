package com.example.respringboot.converters;

import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.model.Project;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProjectToProjectCommand implements Converter<Project, ProjectCommand> {
    private final LocationToLocationCommand locationConverter;
    private final BuildingToBuildingCommand buildingConverter;

    public ProjectToProjectCommand(LocationToLocationCommand locationConverter, BuildingToBuildingCommand buildingConverter) {
        this.locationConverter = locationConverter;
        this.buildingConverter = buildingConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public ProjectCommand convert(Project source) {

        if (source == null) {
            return null;
        }

        final ProjectCommand projectCommand = new ProjectCommand();
        projectCommand.setId(source.getProjectCode());
        if (source.getCompany() != null) {
            projectCommand.setCompanyCode(source.getCompany().getCompanyCode());
        }
        if (source.getProfitCenter() != null) {
            projectCommand.setProfitCode(source.getProfitCenter().getProfitCode());
        }
        projectCommand.setId(source.getProjectCode());
        projectCommand.setProjectId(source.getProjectId());
        projectCommand.setProjectDescription(source.getProjectDescription());
        projectCommand.setValidFrom(source.getValidFrom());
        projectCommand.setProfit(source.getProfit());
        projectCommand.setLocationCommand(locationConverter.convert(source.getLocation()));
        if (source.getBuildings() != null && source.getBuildings().size() > 0){
            source.getBuildings()
                    .forEach(building -> projectCommand.getBuildingCommands().add(buildingConverter.convert(building)));
        }
        return projectCommand;

    }
}
