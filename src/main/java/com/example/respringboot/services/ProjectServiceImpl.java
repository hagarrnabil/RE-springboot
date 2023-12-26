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

    @Override
    @Transactional
    public ProjectCommand updatePC(Long l) {

        ProjectCommand projectCommand = findProjectCommandById(l);
        log.debug(projectCommand.getProjectDescription());
        Project detachedProject = projectCommandToProject.convert(projectCommand);
        Project savedProject = projectRepository.save(detachedProject);
        return projectToProjectCommand.convert(savedProject);
    }

    @Override
    @Synchronized
    @Nullable
    @Transactional
    public Project updateProject(Project newProject, Long l) {
        return projectRepository.findById(l).map(oldProject -> {
            if (newProject.getProjectId() != oldProject.getProjectId()) oldProject.setProjectId(newProject.getProjectId());
            if (newProject.getProjectDescription() != oldProject.getProjectDescription()) oldProject.setProjectDescription(newProject.getProjectDescription());
            if (newProject.getProfit() != oldProject.getProfit()) oldProject.setProfit(newProject.getProfit());
            if (newProject.getValidFrom() != oldProject.getValidFrom()) oldProject.setValidFrom(newProject.getValidFrom());
            if ((newProject.getCompanyCode() != null)) {
                Company company = new Company();
                company.setCompanyCode(newProject.getCompanyCode());
                oldProject.setCompany(company);
                company.addProject(oldProject);
            }
            if ((newProject.getProfitCode() != null)) {
                ProfitCenter profitCenter = new ProfitCenter();
                profitCenter.setProfitCode(newProject.getProfitCode());
                oldProject.setProfitCenter(profitCenter);
                profitCenter.addProject(oldProject);
            }
            if ((newProject.getLocationCode() != null)) {
                Location location = new Location();
                location.setLocationCode(newProject.getLocationCode());
                oldProject.setLocation(location);
                location.setProject(oldProject);
            }
            return projectRepository.save(oldProject);
        }).orElseThrow(() -> new RuntimeException("Project not found"));
    }


    @Override
    @Transactional
    public ProjectCommand findProjectCommandById(Long l) {
        return projectToProjectCommand.convert(findById(l));
    }
}
