package com.intra.team.service;


import com.intra.team.dto.*;
import com.intra.team.entity.Team;

import java.util.List;

public interface TeamService {

    Team createTeamUnderProject(TeamCreateRequest request, String createdBy);

//    List<Team> listTeams(String projectId);
ProjectTeamsDTO getTeamsByProjectName(String projectName);

    Team addUsersToTeam(AddUsersToTeamRequest req);

    TeamUsersResponse getUsersByTeam(
            String projectName,
            String teamName,
            String teamType
    );
}

