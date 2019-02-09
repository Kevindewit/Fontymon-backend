package io.kevindewit.service.prototype.service;

import io.kevindewit.service.prototype.models.Prototype_Status;
import io.kevindewit.service.prototype.models.Prototype_Type;
import io.kevindewit.service.prototype.repository.TypeRepository;
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

    public List<Prototype_Type> findAllByStatusOrderByName(Prototype_Status status){
        return typeRepository.findAllByStatusOrderByName(status);
    }

    public Boolean existsById(UUID uuid) {
        return typeRepository.existsById(uuid);
    }

    public  void save(Prototype_Type type) {
        typeRepository.save(type);
    }
}
