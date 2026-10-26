package com.smartdocmanager.domain.model;
import java.time.LocalDateTime;
import java.util.UUID;


//Class Metier de document
public class Document {
    private final String id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;

    public Document(String id, String title, String content, String author, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

    // Règle métier : Un document est éligible au résumé s'il fait plus de 200 caractères
    public boolean isSummaryEligible() {
        return this.content != null && this.content.length() > 200;
    }


    // getters
    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Méthode métier dans l'objet Domaine pour créer un nouveau document
    public static Document createNew(String title, String author, String content) {
        return new Document(
                UUID.randomUUID().toString(),
                title,
                content,
                author,
                LocalDateTime.now()
        );
    }
}

