package dev.shingi.Endpoints.Models;

public class Kostenplaats {
    private String omschrijving;
    private boolean nonactief;
    private int nummer;
    private String id;
    private String uri;

    // Getters and setters

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public boolean isNonactief() {
        return nonactief;
    }

    public void setNonactief(boolean nonactief) {
        this.nonactief = nonactief;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
