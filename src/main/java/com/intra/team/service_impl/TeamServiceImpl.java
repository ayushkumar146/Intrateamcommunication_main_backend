package com.intra.team.service_impl;


import com.intra.team.dto.*;
import com.intra.team.entity.Project;
import com.intra.team.entity.Team;
import com.intra.team.exceptions.ProjectAlreadyExistsException;
import com.intra.team.exceptions.ResourceNotFoundException;
import com.intra.team.repository.ProjectRepository;
import com.intra.team.repository.TeamRepository;
import com.intra.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

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
                        new ResourceNotFoundException("Project not found"));

        List<TeamSummaryResponse> teams =
                teamRepository.findByProjectId(project.getId())
                        .stream()
                        .map(t -> new TeamSummaryResponse(
                                t.getName(),
                                t.getType()))
                        .toList();

        return new ProjectTeamsDTO(project.getName(), teams);
    }
    @Override
    public Team addUsersToTeam(AddUsersToTeamRequest req) {

        // find project by name
        Project project = projectRepository.findByName(req.getProjectName())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        // find team inside project
        Team team = teamRepository
                .findByProjectIdAndNameAndType(
                        project.getId(),
                        req.getTeamName(),
                        req.getTeamType())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team not found"));

        // init list if null
        if (team.getUserEmails() == null) {
            team.setUserEmails(new ArrayList<>());
        }

        // avoid duplicates
        for (String email : req.getUserEmails()) {
            if (!team.getUserEmails().contains(email)) {
                team.getUserEmails().add(email);
            }
        }

        return teamRepository.save(team);
    }

    @Override
    public TeamUsersResponse getUsersByTeam(
            String projectName,
            String teamName,
            String teamType) {

        // case-sensitive — exact match
        Project project = projectRepository.findByName(projectName)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Project not found"));

        Team team = teamRepository
                .findByProjectIdAndNameAndType(
                        project.getId(),
                        teamName,
                        teamType)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Team not found"));

        List<String> users = team.getUserEmails() == null
                ? List.of()
                : team.getUserEmails();

        return new TeamUsersResponse(
                projectName,
                teamName,
                teamType,
                users
        );
    }
}


