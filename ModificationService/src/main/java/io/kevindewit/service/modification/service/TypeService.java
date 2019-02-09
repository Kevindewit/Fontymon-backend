package io.kevindewit.service.modification.service;

import io.kevindewit.service.modification.models.Modification_Status;
import io.kevindewit.service.modification.models.Modification_Type;
import io.kevindewit.service.modification.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TypeService {

    private final TypeRepository typeRepository;

    @Autowired
    public TypeService(TypeRepository typeRepository) { this.typeRepository = typeRepository; }

    public void save(Modification_Type type) {
        typeRepository.save(type);
    }

    public List<Modification_Type> findAllByStatusOrderByName(Modification_Status status){
        return typeRepository.findAllByStatusOrderByName(status);
    }

    public List<Modification_Type> findAllByStatusAndName(Modification_Status status, String name){
        //TODO: remove UUID.randomUUID() with UUID received from PrototypeService.
        return typeRepository.findAllByStatusAndPrototypeUuid(status, UUID.randomUUID());
    }

}
