package com.example.respringboot.services;

import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.converters.*;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.Location;
import com.example.respringboot.model.ProfitCenter;
import com.example.respringboot.model.Project;
import com.example.respringboot.repositories.ProjectRepository;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@Component
public class ProjectServiceImpl implements ProjectService {
    private final ProjectToProjectCommand projectToProjectCommand;
    private final ProjectCommandToProject projectCommandToProject;
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectToProjectCommand projectToProjectCommand, ProjectCommandToProject projectCommandToProject,
                              ProjectRepository projectRepository) {
        this.projectToProjectCommand = projectToProjectCommand;
        this.projectCommandToProject = projectCommandToProject;
        this.projectRepository = projectRepository;
    }


    @Override
    @Transactional
    public Set<ProjectCommand> getProjectCommands() {
        return StreamSupport.stream(projectRepository.findAll()
                        .spliterator(), false)
                .map(projectToProjectCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public Project findById(Long l) {
        Optional<Project> projectOptional = projectRepository.findById(l);

        if (!projectOptional.isPresent()) {
            throw new RuntimeException("Project Not Found!");
        }

        return projectOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        projectRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public ProjectCommand saveProjectCommand(ProjectCommand command) {

        Project detachedProject = projectCommandToProject.convert(command);
        Project savedProject = projectRepository.save(detachedProject);
        log.debug("Saved Company Id:" + savedProject.getProjectCode());
        return projectToProjectCommand.convert(savedProject);

    }

    public Project updateProject(Project project, Long l) {

        return projectRepository.findById(l).map(project1 -> {
            if (project.getProjectCode() != null) project.setProjectCode(project.getProjectCode());
            if (project.getProjectId() != null) project.setProjectId(project.getProjectId());
            if (project.getProjectDescription() != null) project.setProjectDescription(project.getProjectDescription());
            if (project.getProfit() != null) project.setProfit(project.getProfit());
            if (project.getValidFrom() != null) project.setValidFrom(project.getValidFrom());
            if (project.getCompanyCode() != null) {
                Company company = new Company();
                company.setCompanyCode(project.getCompanyCode());
                project.setCompany(company);
                company.addProject(project);
            }
            if (project.getProfitCode() != null) {
                ProfitCenter profitCenter = new ProfitCenter();
                profitCenter.setProfitCode(project.getProfitCode());
                project.setProfitCenter(profitCenter);
                profitCenter.addProject(project);
            }
            if (project.getLocationCode() != null) {
                Location location = new Location();
                location.setLocationCode(project.getLocationCode());
                project.setLocation(location);
                location.setProject(project);
            }
            return projectRepository.save(project);
        }).orElseGet(() -> {
            project.setProjectCode(l);
            return projectRepository.save(project);
        });
    }

    @Override
    @Transactional
    public ProjectCommand findProjectCommandById(Long l) {
        return projectToProjectCommand.convert(findById(l));
    }
}
