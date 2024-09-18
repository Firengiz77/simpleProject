package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.dto.ProjectDto;
import com.bookstore.simpleblog.dto.request.ProjectRequest;
import com.bookstore.simpleblog.exceptions.ProjectNotFoundException;
import com.bookstore.simpleblog.mapper.ProjectMapper;
import com.bookstore.simpleblog.model.Image;
import com.bookstore.simpleblog.model.Project;
import com.bookstore.simpleblog.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectMapper projectMapper;
    private final ProjectRepository projectRepository;
    private final ImageService imageService;

    public List<ProjectDto> getAllProjects() {
        return projectMapper.toDto(projectRepository.findAll());
    }

    public ProjectDto getProjectById(Long id) {
        return projectMapper.toDto(projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException(id)));
    }

    public ProjectDto createProject(ProjectRequest projectRequest) {
        Image image1 = imageService.create(projectRequest.getImage());
       Project project = Project.builder()
               .name(projectRequest.getName())
               .description(projectRequest.getDescription())
               .slug(projectRequest.getSlug())
               .image(image1)
               .build();
        return projectMapper.toDto(projectRepository.save(project));
    }

    public void updateProject(Long id, ProjectRequest projectRequest) throws IOException {
        Project project = projectRepository.findById(id).orElseThrow(()->new ProjectNotFoundException(id));
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setSlug(projectRequest.getSlug());
        Image image1 = imageService.update(project.getImage().getId(),projectRequest.getImage());
        project.setImage(image1);
        projectRepository.save(project);
    }

    public HttpStatus deleteProject(Long id) {
        if(projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_FOUND;
        }
    }
}
