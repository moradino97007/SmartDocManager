package com.smartdocmanager.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DocumentRequest(
        @NotBlank(message = "Le titre ne peut pas être vide")
        @Size(min = 3, max = 100, message = "Le titre doit contenir entre 3 et 100 caractères")
        String title,
        @NotBlank(message = "Le contenu ne peut pas être vide")
        String content,
        @NotBlank(message = "L'auteur du document doit exister")
        String author) {
}
