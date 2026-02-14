package com.intra.team.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChecklistRequest {

    private String projectName;
    private String teamName;
    private String teamType;

    private List<String> points;
}

