package com.example.resource_tracker.service;

import com.example.resource_tracker.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDto> getAll() {
        return (List<ProjectDto>) projectRepository.findAll();
    }

    public void delete(Integer id) {
        projectRepository.delete(projectRepository.findById(id).get());
    }

    public ProjectDto save(ProjectDto project) {
        return projectRepository.save(project);
    }
}
