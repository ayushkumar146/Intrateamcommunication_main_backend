package com.intra.team.service_impl;

import com.intra.team.dto.ChecklistRequest;
import com.intra.team.entity.Project;
import com.intra.team.entity.TeamChecklist;
import com.intra.team.exceptions.ProjectAlreadyExistsException;
import com.intra.team.exceptions.ResourceNotFoundException;
import com.intra.team.repository.ProjectRepository;
import com.intra.team.repository.TeamChecklistRepository;
import com.intra.team.service.ChecklistService;
import com.intra.team.utils.ChecklistItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChecklistServiceImpl implements ChecklistService {

    private final ProjectRepository projectRepo;
    private final TeamChecklistRepository repo;

    @Override
    public TeamChecklist createChecklist(ChecklistRequest req) {

        Project project = projectRepo.findByName(req.getProjectName())
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        List<ChecklistItem> items =
                req.getPoints().stream()
                        .map(p -> new ChecklistItem(p, false))
                        .toList();

        TeamChecklist checklist = TeamChecklist.builder()
                .projectName(project.getName())
                .projectId(project.getId())
                .teamName(req.getTeamName())
                .teamType(req.getTeamType())
                .items(items)
                .build();

        return repo.save(checklist);
    }

    @Override
    public TeamChecklist getChecklist(
            String projectName,
            String team,
            String type) {

        Project project = projectRepo.findByName(projectName)
                .orElseThrow(() -> new ResourceNotFoundException("Project not found"));

        return repo.findByProjectIdAndTeamNameAndTeamType(
                        project.getId(),
                        team,
                        type)
                .orElseThrow(() -> new ResourceNotFoundException("Checklist not found"));
    }

    @Override
    public TeamChecklist updateItemStatus(
            String checklistId,
            int index,
            boolean status) {

        TeamChecklist c = repo.findById(checklistId)
                .orElseThrow(() -> new ResourceNotFoundException("Checklist not found"));

        c.getItems().get(index).setCompleted(status);

        return repo.save(c);
    }
}

