package com.intra.team.entity;

import com.intra.team.utils.ChecklistItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("team_checklists")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamChecklist {

    @Id
    private String id;

    private String projectId;
    private String projectName;
    private String teamName;
    private String teamType;

    private List<ChecklistItem> items;   // ← list<pair<string,boolean>>
}

