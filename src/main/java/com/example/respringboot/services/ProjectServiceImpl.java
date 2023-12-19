package com.example.respringboot.services;

import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.converters.*;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.Location;
import com.example.respringboot.model.ProfitCenter;
import com.example.respringboot.model.Project;
import com.example.respringboot.repositories.CompanyRepository;
import com.example.respringboot.repositories.LocationRepository;
import com.example.respringboot.repositories.ProjectRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
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
    public Project updateProject(Project project, Long l) {
        ProfitCenter profitCenter = new ProfitCenter();
        Company company = new Company();
        Location location = new Location();

        return projectRepository.findById(l).map(project1 -> {
                    if (project.getProjectCode() != null) project1.setProjectCode(project.getProjectCode());
                    if (project.getProjectId() != null) project1.setProjectId(project.getProjectId());
                    if (project.getProjectDescription() != null) project1.setProjectDescription(project.getProjectDescription());
                    if (project.getProfit() != null) project1.setProfit(project.getProfit());
                    if (project.getValidFrom() != null) project1.setValidFrom(project.getValidFrom());
                    if (profitCenter.addProject(project) != null) profitCenter.addProject(project);
                    if (project.getLocation() != null) project1.setLocation(project.getLocation());
                    if (company.addProject(project) != null) company.addProject(project);
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
