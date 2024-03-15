package com.malexj.repository;

import com.malexj.model.entity.RssTopicEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RssTopicRepository extends ReactiveMongoRepository<RssTopicEntity, String> {}
