package com.smartdocmanager.domain.sevice;

import com.smartdocmanager.domain.model.Document;
import com.smartdocmanager.domain.port.output.DocumentOutputPort;
import com.smartdocmanager.domain.exeption.DocumentNotFoundException;
import com.smartdocmanager.infrastructure.adapter.in.web.dto.DocumentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {


    private final DocumentOutputPort documentOutputPort; // Interdictions d'injecter JpaRepository ici !

    public DocumentService(DocumentOutputPort documentOutputPort) {
        this.documentOutputPort = documentOutputPort;
    }

    public Document createDocument(DocumentRequest document) {
        // 1. Règles métier (valider le titre, formater le contenu...)
        var newDocument = Document.createNew(document.title(), document.content(), document.author());
        // 2. Appel du port de sortie
        return documentOutputPort.save(newDocument);
    }

    public List<Document> findAll(){
        return documentOutputPort.findAll();
    }


    public Document findById(String id){
        return documentOutputPort.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
    }
}
