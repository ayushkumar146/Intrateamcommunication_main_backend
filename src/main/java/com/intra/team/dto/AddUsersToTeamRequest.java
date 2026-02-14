package com.intra.team.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddUsersToTeamRequest {

    private String projectName;
    private String teamName;
    private String teamType;

    private List<String> userEmails;
}
