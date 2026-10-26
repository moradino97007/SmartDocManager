package com.smartdocmanager.infrastructure.adapter.in.web.dto;
import java.time.LocalDateTime;

public record DocumentResponse(String id,
                               String title,
                               String author,
                               boolean summaryEligible,
                               LocalDateTime createdAt
) { }
