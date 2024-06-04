package com.example.resource_tracker.controller;

import com.example.resource_tracker.data.model.Project;
import com.example.resource_tracker.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        projectService.delete(id);
    }

    @PostMapping("/")
    public Project add(@RequestBody Project project) {
        return projectService.save(project);
    }

    @PutMapping("/")
    public Project update(@RequestBody Project project) {
        return projectService.save(project);
    }
}
