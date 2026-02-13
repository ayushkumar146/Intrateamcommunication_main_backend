package com.intra.team.service_impl;


import com.intra.team.dto.ProjectTeamsDTO;
import com.intra.team.dto.TeamCreateRequest;
import com.intra.team.dto.TeamSummaryResponse;
import com.intra.team.entity.Project;
import com.intra.team.entity.Team;
import com.intra.team.repository.ProjectRepository;
import com.intra.team.repository.TeamRepository;
import com.intra.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

//import java.util.List;

@Service
@RequiredArgsConstructor
public class  TeamServiceImpl implements TeamService {

    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;

    @Override
    public Team createTeamUnderProject(TeamCreateRequest request, String createdBy) {

        Project project = projectRepository
                .findByName(request.getProjectName())
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Team team = new Team();
        team.setName(request.getTeamName());
        team.setType(request.getType());
        team.setProjectId(project.getId());
        team.setProjectName(project.getName());
        team.setCreatedBy(createdBy);

        return teamRepository.save(team);
    }

    @Override
    public ProjectTeamsDTO getTeamsByProjectName(String projectName) {



        Project project = projectRepository
                .findByName(projectName)
                .orElseThrow(() ->
                        new RuntimeException("Project not found"));

        List<TeamSummaryResponse> teams =
                teamRepository.findByProjectId(project.getId())
                        .stream()
                        .map(t -> new TeamSummaryResponse(
                                t.getName(),
                                t.getType()))
                        .toList();

        return new ProjectTeamsDTO(project.getName(), teams);
    }
}


