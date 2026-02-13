package com.intra.team.controllers;

import com.intra.team.dto.CreateProjectRequest;
import com.intra.team.entity.Project;
import com.intra.team.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project/admin")
@RequiredArgsConstructor
public class AdminProjectController {

    private final ProjectService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Project create(@RequestBody CreateProjectRequest req,
                          Authentication auth) {

        return service.createProject(req, auth.getName());
    }
}
