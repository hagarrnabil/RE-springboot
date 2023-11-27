package com.example.respringboot.repositories;

import com.example.respringboot.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project,Long> {
}
