package com.smartdocmanager.domain.exeption;

public class DocumentNotFoundException extends RuntimeException{
    public DocumentNotFoundException(String id){
        super("Document with id : "+id+" not found");
    }
}
