package com.example.respringboot.repositories;

import com.example.respringboot.commands.ProjectCommand;
import com.example.respringboot.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project,Long> {
    @Query("SELECT p FROM Project p WHERE CONCAT(p.id, ' ', p.validFrom, ' ', p.profit, ' ', p.projectDescription,' ',p.projectId, ' ',p.buildings, ' ',p.company, ' ',p.location" +
            ",' ',p.profitCenter) LIKE %?1%")
    public List<ProjectCommand> search(String keyword);
}
