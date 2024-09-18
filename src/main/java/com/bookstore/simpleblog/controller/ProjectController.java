package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.dto.ProjectDto;
import com.bookstore.simpleblog.dto.request.ProjectRequest;
import com.bookstore.simpleblog.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor

public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ProjectDto createProject(ProjectRequest projectRequest)  {
        return projectService.createProject(projectRequest);
    }

    @PutMapping("/{id}")
    public void updateProject(@PathVariable Long id, ProjectRequest projectRequest) throws IOException {
        projectService.updateProject(id,projectRequest);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteProject(@PathVariable Long id) {
      return   projectService.deleteProject(id);
    }
}
