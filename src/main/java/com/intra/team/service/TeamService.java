package com.intra.team.service;


import com.intra.team.dto.ProjectTeamsDTO;
import com.intra.team.dto.TeamCreateRequest;
import com.intra.team.dto.TeamSummaryResponse;
import com.intra.team.entity.Team;

import java.util.List;

public interface TeamService {

    Team createTeamUnderProject(TeamCreateRequest request, String createdBy);

//    List<Team> listTeams(String projectId);
ProjectTeamsDTO getTeamsByProjectName(String projectName);
}

