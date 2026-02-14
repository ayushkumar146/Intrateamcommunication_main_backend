package com.intra.team.repository;

import com.intra.team.entity.TeamChecklist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamChecklistRepository
        extends MongoRepository<TeamChecklist, String> {

    Optional<TeamChecklist> findByProjectIdAndTeamNameAndTeamType(
            String projectId,
            String teamName,
            String teamType
    );
}

