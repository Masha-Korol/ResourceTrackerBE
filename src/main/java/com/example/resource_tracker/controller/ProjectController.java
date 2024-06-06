package com.example.resource_tracker.controller;

import com.example.resource_tracker.data.model.Project;
import com.example.resource_tracker.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public List<ProjectDto> getAll() {
        return projectService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        projectService.delete(id);
    }

    @PostMapping("/")
    public ProjectDto add(@RequestBody ProjectDto project) {
        return projectService.save(project);
    }

    @PutMapping("/")
    public ProjectDto update(@RequestBody ProjectDto project) {
        return projectService.save(project);
    }
}
