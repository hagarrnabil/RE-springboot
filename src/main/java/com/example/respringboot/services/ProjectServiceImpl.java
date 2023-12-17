package com.example.respringboot.services;

import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.converters.LocationCommandToLocation;
import com.example.respringboot.converters.ProjectCommandToProject;
import com.example.respringboot.converters.ProjectToProjectCommand;
import com.example.respringboot.model.Company;
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
    private final LocationCommandToLocation locationConverter;
    private final ProjectRepository projectRepository;
    private final CompanyRepository companyRepository;
    private final LocationRepository locationRepository;

    public ProjectServiceImpl(ProjectToProjectCommand projectToProjectCommand, ProjectCommandToProject projectCommandToProject, LocationCommandToLocation locationConverter,
                              ProjectRepository projectRepository, CompanyRepository companyRepository, LocationRepository locationRepository) {
        this.projectToProjectCommand = projectToProjectCommand;
        this.projectCommandToProject = projectCommandToProject;
        this.locationConverter = locationConverter;
        this.projectRepository = projectRepository;
        this.companyRepository = companyRepository;
        this.locationRepository = locationRepository;
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

//    @Override
//    @Transactional
//    public Project updateProject(Project project, Long l) {
//
//        return projectRepository.findById(l).map(project1 -> {
//            project1.setProjectId(project.getProjectId());
//            project1.setProjectDescription(project.getProjectDescription());
//            project1.setProfit(project.getProfit());
//            project1.setValidFrom(project.getValidFrom());
//            project1.setc
//            return projectRepository.save(project);
//        }).orElseGet(() -> {
//            project.setOrientation_code(l);
//            return projectRepository.save(project);
//        });
//    }

    @Override
    @Transactional
    public ProjectCommand findProjectCommandById(Long l) {
        return projectToProjectCommand.convert(findById(l));
    }
}
