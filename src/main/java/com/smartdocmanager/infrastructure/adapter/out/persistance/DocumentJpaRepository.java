package com.smartdocmanager.infrastructure.adapter.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.*;


@Repository
public interface DocumentJpaRepository extends JpaRepository<DocumentEntity, String> {

}
