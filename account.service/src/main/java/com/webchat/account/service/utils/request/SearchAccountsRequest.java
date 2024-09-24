package com.webchat.account.service.utils.request;

import com.webchat.account.service.domain.models.Country;
import com.webchat.account.service.domain.models.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class SearchAccountsRequest {
    private String name;
    private String surname;
    private List<Long> musicGenresIds;
    private Long genderId;
}
