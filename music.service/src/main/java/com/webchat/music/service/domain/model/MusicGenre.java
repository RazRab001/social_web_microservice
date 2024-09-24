package com.webchat.music.service.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("MusicGenre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MusicGenre {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public MusicGenre(String name){
        this.name = name;
    }
}