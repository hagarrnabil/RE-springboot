package com.example.respringboot.converters;

import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.Location;
import com.example.respringboot.model.ProfitCenter;
import com.example.respringboot.model.Project;
import com.example.respringboot.repositories.ProjectRepository;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProjectCommandToProject implements Converter<ProjectCommand, Project> {
    private final BuildingCommandToBuilding buildingConverter;
    private final ProjectRepository projectRepository;

    public ProjectCommandToProject(BuildingCommandToBuilding buildingConverter, ProjectRepository projectRepository) {
        this.buildingConverter = buildingConverter;
        this.projectRepository = projectRepository;
    }

    @Synchronized
    @Nullable
    @Override
    public Project convert(ProjectCommand source) {

        if (source == null) {
            return null;
        }

        final Project project = new Project();
        project.setProjectCode(source.getProjectCode());
        if (source.getCompanyCode() != null) {
            Company company = new Company();
            company.setCompanyCode(source.getCompanyCode());
            project.setCompany(company);
            company.addProject(project);
        }
        if (source.getProfitCode() != null) {
            ProfitCenter profitCenter = new ProfitCenter();
            profitCenter.setProfitCode(source.getProfitCode());
            project.setProfitCenter(profitCenter);
            profitCenter.addProject(project);
        }
        if (source.getLocationCode() != null) {
            Location location = new Location();
            location.setLocationCode(source.getLocationCode());
            project.setLocation(location);
            location.setProject(project);
        }
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

    public Optional<ProjectCommand> convert(Optional<ProjectCommand> projectCommand) {
        if (projectCommand == null)
            return null;
        final Project project = new Project();
        project.setProjectCode(projectCommand.get().getProjectCode());
        if (projectCommand.get().getCompanyCode() != null) {
            Company company = new Company();
            company.setCompanyCode(projectCommand.get().getCompanyCode());
            project.setCompany(company);
            company.addProject(project);
        }
        if (projectCommand.get().getProfitCode() != null) {
            ProfitCenter profitCenter = new ProfitCenter();
            profitCenter.setProfitCode(projectCommand.get().getProfitCode());
            project.setProfitCenter(profitCenter);
            profitCenter.addProject(project);
        }
        if (projectCommand.get().getLocationCode() != null) {
            Location location = new Location();
            location.setLocationCode(projectCommand.get().getLocationCode());
            project.setLocation(location);
            location.setProject(project);
        }
        project.setProjectId(projectCommand.get().getProjectId());
        project.setProjectDescription(projectCommand.get().getProjectDescription());
        project.setValidFrom(projectCommand.get().getValidFrom());
        project.setProfit(projectCommand.get().getProfit());
        if (projectCommand.get().getBuildingCommands() != null && projectCommand.get().getBuildingCommands().size() > 0) {
            projectCommand.get().getBuildingCommands()
                    .forEach(buildingCommand -> project.getBuildings().add(buildingConverter.convert(buildingCommand)));
        }
        return projectCommand;

    }


//    public Project convert(Optional<Project> project) {
//        if (project != null)
//            return project.get();
//        else
//            return null;
//    }

//    public ProjectCommand convert(ProjectCommand projectCommand) {
//        if (projectCommand == null)
//            return null;
//        else
//            return null;
//    }
}
