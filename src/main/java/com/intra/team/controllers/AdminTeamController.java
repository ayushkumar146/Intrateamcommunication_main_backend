package com.intra.team.controllers;
import com.intra.team.dto.AddUsersToTeamRequest;
import com.intra.team.dto.TeamCreateRequest;
import com.intra.team.dto.TeamSummaryResponse;
import com.intra.team.entity.Team;
import com.intra.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/team")
@RequiredArgsConstructor
public class AdminTeamController {

    private final TeamService teamService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createTeam(
            @RequestBody TeamCreateRequest request,
            Authentication auth) {

        Team team = teamService.createTeamUnderProject(
                request,
                auth.getName()
        );

        return ResponseEntity.ok(team);
    }

    @PostMapping("/add-users")
    @PreAuthorize("hasRole('ADMIN')")
    public Team addUsers(@RequestBody AddUsersToTeamRequest req) {
        return teamService.addUsersToTeam(req);

    }
}

