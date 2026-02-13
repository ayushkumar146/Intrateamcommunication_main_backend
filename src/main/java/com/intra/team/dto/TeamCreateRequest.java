package com.intra.team.dto;

import lombok.Data;

@Data
public class TeamCreateRequest {

    private String projectName;
    private String teamName;
    private String type;
}
