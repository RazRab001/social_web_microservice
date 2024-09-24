package com.webchat.account.service.domain.models;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;

@Node("Account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(generatorClass = GeneratedValue.UUIDGenerator.class)
    private UUID id;

    private String userId;

    private String name = null;

    private String surname = null;

    private Date birth = null;

    private AccountType type = AccountType.People;

    @Relationship(type = "LIKE_MUSIC")
    private List<MusicGenre> musicGenre = emptyList();

    @Relationship(type = "GENDER")
    private Gender gender = null;

    @Relationship(type = "FRIEND", direction = Relationship.Direction.OUTGOING)
    private List<Account> friends = emptyList();

    @Relationship(type = "SUB", direction = Relationship.Direction.OUTGOING)
    private List<Account> subscribers = emptyList();

    @Relationship(type = "WORK", direction = Relationship.Direction.OUTGOING)
    private Account work = null;

    @Relationship(type = "BORN_IN")
    private Country bornIn = null;

    @Relationship(type = "LIVE_IN")
    private Country liveIn = null;

    public Account(String userId) {
        this.userId = userId;
    }

    public Account(String name, String surname, Date birth,
                   Gender gender, Country bornIn, Country liveIn){
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.gender = gender;
        this.bornIn = bornIn;
        this.liveIn = liveIn;
    }

    public void addFriend(Account account){
        if(!this.friends.contains(account)){
            this.friends.add(account);
        }
    }
}
