package io.kevindewit.service.modification.repository;

import io.kevindewit.service.modification.models.Modification_Status;
import io.kevindewit.service.modification.models.Modification_Type;

import java.util.List;
import java.util.UUID;

public interface TypeRepository extends ModificationRepository<Modification_Type, UUID> {
    List<Modification_Type> findAllByModifiedBy_UsernameOrderByName(String username);
    List<Modification_Type> findAllByStatusOrderByName(Modification_Status status);
    List<Modification_Type> findAllByStatusAndPrototypeUuid(Modification_Status status, UUID uuid);
}
