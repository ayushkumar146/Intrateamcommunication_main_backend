package com.intra.team.service;

import com.intra.team.dto.ChecklistRequest;
import com.intra.team.entity.TeamChecklist;

public interface ChecklistService {

    TeamChecklist createChecklist(ChecklistRequest req);

    TeamChecklist getChecklist(String project, String team, String type);

    TeamChecklist updateItemStatus(
            String checklistId,
            int index,
            boolean status
    );
}
