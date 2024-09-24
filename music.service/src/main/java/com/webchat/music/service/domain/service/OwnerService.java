package com.webchat.music.service.domain.service;

import com.webchat.music.service.domain.model.Owner;
import com.webchat.music.service.domain.repository.OwnerRepository;
import com.webchat.music.service.domain.service.Inter.IOwnerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OwnerService implements IOwnerService {
    private final OwnerRepository repository;
    public List<Owner> fetchOwners(){
        return repository.findAll();
    }

    @Override
    public Owner getOwnerById(UUID id) {
        Owner owner = repository.getOwnerById(id);
        if(owner == null){
            throw new RuntimeException("Account doesn't exist");
        }
        return owner;
    }
}
