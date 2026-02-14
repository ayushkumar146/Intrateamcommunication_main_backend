package com.intra.team.controllers;

import com.intra.team.dto.ChecklistRequest;
import com.intra.team.entity.TeamChecklist;
import com.intra.team.service.ChecklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checklist")
@RequiredArgsConstructor
public class ChecklistController {

    private final ChecklistService service;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public TeamChecklist create(
            @RequestBody ChecklistRequest req) {
        return service.createChecklist(req);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public TeamChecklist get(
            @RequestParam String project,
            @RequestParam String team,
            @RequestParam String type) {

        return service.getChecklist(project, team, type);
    }

    @PutMapping("/item")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public TeamChecklist mark(
            @RequestParam String id,
            @RequestParam int index,
            @RequestParam boolean done) {

        return service.updateItemStatus(id, index, done);
    }
}

