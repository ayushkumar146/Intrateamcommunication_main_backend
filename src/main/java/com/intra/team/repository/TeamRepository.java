package com.intra.team.repository;

import com.intra.team.entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository
        extends MongoRepository<Team, String> {

//    List<TeamNameTypeProjection> findAllBy();
    List<Team> findByProjectId(String projectId);
    Optional<Team> findByProjectIdAndNameAndType(
            String projectId,
            String name,
            String type
    );
}
