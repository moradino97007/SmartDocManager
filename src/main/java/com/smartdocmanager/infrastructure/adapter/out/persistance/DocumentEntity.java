package com.smartdocmanager.infrastructure.adapter.out.persistance;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "documents")
public class DocumentEntity {
    @Getter
    @Id
    private String id;
    @Getter
    private String title;
    @Getter
    private String content;
    @Getter
    private String author;
    @Getter
    private LocalDateTime creatAt;


    public DocumentEntity(String id, String title, String content, String author, LocalDateTime creatAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.creatAt = creatAt;
    }




    public DocumentEntity() {

    }


}
