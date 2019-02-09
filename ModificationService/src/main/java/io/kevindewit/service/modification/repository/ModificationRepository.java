package io.kevindewit.service.modification.repository;

import io.kevindewit.service.modification.models.Modification_Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface ModificationRepository <T extends Modification_Base, ID extends Serializable> extends JpaRepository<T,ID> {
}
