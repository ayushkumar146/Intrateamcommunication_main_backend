package com.intra.team.repository;

import com.intra.team.entity.Team;
import com.intra.team.utils.TeamNameTypeProjection;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TeamRepository
        extends MongoRepository<Team, String> {

//    List<TeamNameTypeProjection> findAllBy();
    List<Team> findByProjectId(String projectId);
}
