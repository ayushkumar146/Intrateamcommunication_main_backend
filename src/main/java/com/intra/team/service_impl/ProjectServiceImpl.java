package com.intra.team.service_impl;

import com.intra.team.dto.CreateProjectRequest;
import com.intra.team.entity.Project;
import com.intra.team.repository.ProjectRepository;
import com.intra.team.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository repo;

    @Override
    public Project createProject(CreateProjectRequest req,
                                 String createdBy) {
        if (repo.existsByName(req.getName())) {
            throw new RuntimeException("Project already exists with this name");
        }
        Project p = Project.builder()
                .name(req.getName())
                .description(req.getDescription())
                .createdBy(createdBy)
                .build();

        return repo.save(p);
    }

    @Override
    public List<Project> getAllProjects() {
        return repo.findAll();
    }
}

