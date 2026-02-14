package com.intra.team.controllers;

import com.intra.team.dto.ProjectTeamsDTO;
import com.intra.team.dto.TeamSummaryResponse;
import com.intra.team.dto.TeamUsersResponse;
import com.intra.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamQueryController {

    private final TeamService teamService;

    @GetMapping("/by-project-name")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ProjectTeamsDTO getTeams(
            @RequestParam String name) {

        return teamService.getTeamsByProjectName(name);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public TeamUsersResponse getUsers(
            @RequestParam String project,
            @RequestParam String team,
            @RequestParam String type) {

        return teamService.getUsersByTeam(project, team, type);
    }
}
