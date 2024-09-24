package com.webchat.music.service.domain.service.Inter;

import com.webchat.music.service.domain.model.Owner;

import java.util.UUID;

public interface IOwnerService {
    Owner getOwnerById(UUID id);
}
