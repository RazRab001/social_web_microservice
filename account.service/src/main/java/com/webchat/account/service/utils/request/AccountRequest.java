package com.webchat.account.service.utils.request;

import com.webchat.account.service.domain.models.Account;
import com.webchat.account.service.domain.models.Country;
import com.webchat.account.service.domain.models.Gender;
import com.webchat.account.service.domain.models.MusicGenre;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.emptyList;

@Data
@AllArgsConstructor
public class AccountRequest {
    private String name;
    private String surname;
    private Date birth;
    private List<String> musicGenre;
    private UUID work;
    private Gender gender;
    private Country bornIn;
    private Country liveIn;

    public Account toAccount(){
        return new Account(
                this.name, this.surname,
                this.birth, this.gender,
                this.bornIn, this.liveIn
        );
    }
}
