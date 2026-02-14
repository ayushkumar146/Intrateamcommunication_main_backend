package com.intra.team.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {

    @Id
    private String id;

    private String name;
    private String type; // frontend/backend/devops

    private String projectId;
    private String projectName;

    private String createdBy;
    private List<String> userEmails;   // or userIds
}