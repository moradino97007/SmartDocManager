package com.smartdocmanager.infrastructure.adapter;

import com.smartdocmanager.domain.model.Document;
import com.smartdocmanager.domain.port.output.DocumentOutputPort;
import com.smartdocmanager.infrastructure.adapter.out.persistance.DocumentEntity;
import com.smartdocmanager.infrastructure.adapter.out.persistance.DocumentJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DocumentJpaAdapter implements DocumentOutputPort {
    private final DocumentJpaRepository repository;

    public DocumentJpaAdapter(DocumentJpaRepository repository){
        this.repository=repository;
    }

    @Override
    public Document save(Document document){
        // 1. Conversion Domaine -> Entity BDD
        var entity = toEntity(document);

        // 2. Sauvegarde via le DAO JPA
        var savedEntity = repository.save(entity);

        // 3. Conversion Entity BDD -> Domaine
        return toDomain(savedEntity);
    }


    @Override
    public List<Document> findAll(){
        return repository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Document> findById(String id){
        return repository.findById(id)
                .map(this::toDomain);
    }


    // Méthodes de mapping d'adaptation
    private DocumentEntity toEntity(Document document) {
        return new DocumentEntity(
                UUID.randomUUID().toString(),
                document.getId(),
                document.getTitle(),
                document.getContent(),
                LocalDateTime.now()
        );
    }

    private Document toDomain(DocumentEntity entity) {
        return new Document(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthor(),
                entity.getCreatAt()
        );
    }
}
