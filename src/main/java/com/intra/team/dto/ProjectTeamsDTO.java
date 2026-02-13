package com.intra.team.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectTeamsDTO {
    private String projectName;
    private List<TeamSummaryResponse> teams;
}

