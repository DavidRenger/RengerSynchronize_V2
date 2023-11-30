package dev.shingi.Endpoints.Models;

import java.util.UUID;

public class Identifier {
    private UUID id;
    private String uri;

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