package com.intra.team.repository;

import com.intra.team.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository
        extends MongoRepository<Project, String> {
}

