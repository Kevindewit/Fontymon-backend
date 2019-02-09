package io.kevindewit.service.prototype.repository;

import io.kevindewit.service.prototype.models.Prototype_Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface PrototypeRepository <T extends Prototype_Base, ID extends Serializable> extends JpaRepository<T, ID> {
}
