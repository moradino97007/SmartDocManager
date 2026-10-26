package com.smartdocmanager;


import com.smartdocmanager.infrastructure.adapter.out.persistance.DocumentEntity;
import com.smartdocmanager.infrastructure.adapter.out.persistance.DocumentJpaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    // 1. Déclaration du champ privé
    private final DocumentJpaRepository documentJpaRepository;

    // 2. Injection par constructeur (Spring va passer le repository ici)
    public DataLoader(DocumentJpaRepository documentJpaRepository) {
        this.documentJpaRepository = documentJpaRepository;
    }
    // 3. Méthode appelée automatiquement au démarrage de l'app
    @Override
    public void run(String... args) throws Exception {
        DocumentEntity entity = new DocumentEntity(
                UUID.randomUUID().toString(),
                "Premier document H2",
                "Contenu de test stocké en BDD mémoire...",
                "Morad",
                LocalDateTime.now()
        );

        documentJpaRepository.save(entity);
    }

}
