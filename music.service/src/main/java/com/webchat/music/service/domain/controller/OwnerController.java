package com.webchat.music.service.domain.controller;

import com.webchat.music.service.domain.model.Owner;
import com.webchat.music.service.domain.service.OwnerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owner")
@AllArgsConstructor
public class OwnerController {
    private final OwnerService ownerService;

    @GetMapping("")
    public List<Owner> fetchOwners(){
        return ownerService.fetchOwners();
    }
}
