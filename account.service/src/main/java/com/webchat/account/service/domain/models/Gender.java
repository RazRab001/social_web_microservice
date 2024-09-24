package com.webchat.account.service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Gender")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gender {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
