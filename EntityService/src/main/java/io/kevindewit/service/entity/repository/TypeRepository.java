package io.kevindewit.service.entity.repository;

import io.kevindewit.service.entity.model.Entity_Type;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TypeRepository extends EntityRepository<Entity_Type, UUID> {
    boolean existsEntity_TypeByUuid (UUID uuid);
    boolean existsEntity_TypeByName (String name);
    Entity_Type findEntity_TypeByName (String name);
}
