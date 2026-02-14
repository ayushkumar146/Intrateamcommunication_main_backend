package com.intra.team.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TeamUsersResponse {

    private String project;
    private String team;
    private String type;
    private List<String> users;
}

