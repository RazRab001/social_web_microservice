package com.webchat.music.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.UUID;

@Node("Account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Owner {
    @Id
    private UUID id;
    private String name;
    private String surname;
}
