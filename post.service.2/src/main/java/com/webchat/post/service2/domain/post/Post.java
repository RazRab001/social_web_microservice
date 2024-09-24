package com.webchat.post.service2.domain.post;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collation = "posts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    private UUID id = UUID.randomUUID();
    private UUID ownerId;
    private String title;
    private String text;
    private Date creationDate = new Date();
    private List<byte[]> images;
    //private List<MultipartFile> files;
}
