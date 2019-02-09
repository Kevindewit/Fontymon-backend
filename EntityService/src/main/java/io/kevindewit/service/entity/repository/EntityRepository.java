package io.kevindewit.service.entity.repository;

import io.kevindewit.service.entity.models.Entity_Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface EntityRepository <T extends Entity_Base, ID extends Serializable> extends JpaRepository<T, ID> {
}
