package com.smartdocmanager.domain.port.output;
import com.smartdocmanager.domain.model.Document;

import java.util.List;
import java.util.Optional;

public interface DocumentOutputPort {
    Document save(Document document);
    Optional<Document> findById(String id);
    List<Document> findAll();
}
