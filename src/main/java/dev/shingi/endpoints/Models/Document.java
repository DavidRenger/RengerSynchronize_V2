package dev.shingi.endpoints.Models;

import java.util.UUID;

public class Document {
    private UUID parentIdentifier;
    private String fileName;
    private boolean readOnly;
    private UUID id;
    private String uri;

    public UUID getParentIdentifier() {
        return parentIdentifier;
    }
    public void setParentIdentifier(UUID parentIdentifier) {
        this.parentIdentifier = parentIdentifier;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public boolean isReadOnly() {
        return readOnly;
    }
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
}