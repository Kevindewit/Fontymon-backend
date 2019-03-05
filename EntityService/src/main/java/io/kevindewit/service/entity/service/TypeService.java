package io.kevindewit.service.entity.service;

import io.kevindewit.service.entity.models.Entity_Type;
import io.kevindewit.service.entity.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TypeService {


    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public void save(Entity_Type entityType) {
        typeRepository.save(entityType);
    }

    public Entity_Type findById(UUID uuid){
        return typeRepository.findById(uuid).get();
    }

    public Entity_Type findEntity_TypeByName(String name) {
        return typeRepository.findEntity_TypeByName(name);
    }

    public boolean existsEntity_TypeByName(String name) {
        return typeRepository.existsEntity_TypeByName(name);
    }

    public List<Entity_Type> findAll() {
        return typeRepository.findAll();
    }

}
