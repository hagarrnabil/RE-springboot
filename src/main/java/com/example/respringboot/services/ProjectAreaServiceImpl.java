package com.example.respringboot.services;

import com.example.respringboot.commands.ProjectAreaCommand;
import com.example.respringboot.converters.ProjectAreaCommandToProjectArea;
import com.example.respringboot.converters.ProjectAreaToProjectAreaCommand;
import com.example.respringboot.model.ProjectArea;
import com.example.respringboot.repositories.ProjectAreaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ProjectAreaServiceImpl implements ProjectAreaService{
    private final ProjectAreaToProjectAreaCommand projectAreaToProjectAreaCommand;
    private final ProjectAreaCommandToProjectArea projectAreaCommandToProjectArea;
    private final ProjectAreaRepository projectAreaRepository;

    public ProjectAreaServiceImpl(ProjectAreaToProjectAreaCommand projectAreaToProjectAreaCommand,
                                  ProjectAreaCommandToProjectArea projectAreaCommandToProjectArea,
                                  ProjectAreaRepository projectAreaRepository)
    {
        this.projectAreaToProjectAreaCommand = projectAreaToProjectAreaCommand;
        this.projectAreaCommandToProjectArea = projectAreaCommandToProjectArea;
        this.projectAreaRepository = projectAreaRepository;
    }

    @Override
    @Transactional
    public Set<ProjectAreaCommand> getProjectAreaCommands() {
        return StreamSupport.stream(projectAreaRepository.findAll()
                        .spliterator(), false)
                .map(projectAreaToProjectAreaCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public ProjectArea findById(Long l) {
        Optional<ProjectArea> projectAreaOptional = projectAreaRepository.findById(l);

        if (!projectAreaOptional.isPresent()) {
            throw new RuntimeException("Project Area Not Found!");
        }

        return projectAreaOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        projectAreaRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public ProjectAreaCommand saveProjectAreaCommand(ProjectAreaCommand command) {

        ProjectArea detachedProjectArea = projectAreaCommandToProjectArea.convert(command);
        ProjectArea savedProjectArea = projectAreaRepository.save(detachedProjectArea);
        log.debug("Saved Project Area Id:" + savedProjectArea.getProjectAreaCode());
        return projectAreaToProjectAreaCommand.convert(savedProjectArea);

    }

    @Override
    public ProjectArea updateProjectArea(ProjectArea projectArea, Long l) {
        return projectAreaRepository.findById(l).map(projectArea1 -> {
            if (projectArea.getProjectAreaCode() != null) projectArea1.setProjectAreaCode(projectArea.getProjectAreaCode());
            if (projectArea.getProjectArea() != null) projectArea1.setProjectArea(projectArea.getProjectArea());
            if (projectArea.getDescription() != null) projectArea1.setDescription(projectArea.getDescription());
            return projectAreaRepository.save(projectArea);
        }).orElseGet(() -> {
            projectArea.setProjectAreaCode(l);
            return projectAreaRepository.save(projectArea);
        });
    }

    @Override
    @Transactional
    public ProjectAreaCommand findProjectAreaCommandById(Long l) {
        return projectAreaToProjectAreaCommand.convert(findById(l));
    }
}
