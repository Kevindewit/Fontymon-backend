package io.kevindewit.service.prototype.repository;

import io.kevindewit.service.prototype.models.Prototype_Status;
import io.kevindewit.service.prototype.models.Prototype_Type;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TypeRepository extends PrototypeRepository<Prototype_Type, UUID> {
    List<Prototype_Type> findAllByStatusOrderByName(Prototype_Status status);
}
