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
    public ProjectCommand updatePC(Optional<Project> project, Long l) {

        project = projectRepository.findById(l);
        Project detachedProject = projectCommandToProject.convert(project);
        Project savedProject = projectRepository.save(detachedProject);
        return projectToProjectCommand.convert(savedProject);

    }

    @Override
    @Synchronized
    @Nullable
    @Transactional
    public Project updateProject(ProjectCommand newProjectCommand, Long l) {
        return projectRepository.findById(l).map(oldProject -> {
//            if (newProjectCommand.getId() != oldProject.getProjectCode()) oldProject.setProjectCode(newProjectCommand.getId());
            if (newProjectCommand.getProjectId() != oldProject.getProjectId()) oldProject.setProjectId(newProjectCommand.getProjectId());
            if (newProjectCommand.getProjectDescription() != oldProject.getProjectDescription()) oldProject.setProjectDescription(newProjectCommand.getProjectDescription());
            if (newProjectCommand.getProfit() != oldProject.getProfit()) oldProject.setProfit(newProjectCommand.getProfit());
            if (newProjectCommand.getValidFrom() != oldProject.getValidFrom()) oldProject.setValidFrom(newProjectCommand.getValidFrom());
            if ((newProjectCommand.getCompanyCode() != null)) {
                Company company = new Company();
                company.setCompanyCode(newProjectCommand.getCompanyCode());
                oldProject.setCompany(company);
                company.addProject(oldProject);
            }
            if ((newProjectCommand.getProfitCode() != null)) {
                ProfitCenter profitCenter = new ProfitCenter();
                profitCenter.setProfitCode(newProjectCommand.getProfitCode());
                oldProject.setProfitCenter(profitCenter);
                profitCenter.addProject(oldProject);
            }
            if ((newProjectCommand.getLocationCode() != null)) {
                Location location = new Location();
                location.setLocationCode(newProjectCommand.getLocationCode());
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
