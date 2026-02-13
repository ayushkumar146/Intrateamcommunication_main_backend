package com.intra.team.controllers;

import com.intra.team.entity.Project;
import com.intra.team.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectQueryController {

    private final ProjectService projectService;

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
}

