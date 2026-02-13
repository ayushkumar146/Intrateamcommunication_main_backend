package com.intra.team.service;

import com.intra.team.dto.CreateProjectRequest;
import com.intra.team.entity.Project;

import java.util.List;

public interface ProjectService {

    Project createProject(CreateProjectRequest req, String createdBy);
    List<Project> getAllProjects();
}
