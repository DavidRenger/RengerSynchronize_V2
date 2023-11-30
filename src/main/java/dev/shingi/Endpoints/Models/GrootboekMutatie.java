package dev.shingi.endpoints.models;

import java.util.List;

public class GrootboekMutatie {
    private Grootboek grootboek;
    private Kostenplaats kostenplaats;
    private String datum;
    private String modifiedOn;
    private Dagboek dagboek;
    private String omschrijving;
    private double debet;
    private double credit;
    private double saldo;
    private List<Document> documents;
    private String boekstuk;
    private String factuurNummer;
    private Identifier relatiePublicIdentifier;
    private String id;
    private String uri;

    // Getters and setters

    public Grootboek getGrootboek() {
        return grootboek;
    }
    public void setGrootboek(Grootboek grootboek) {
        this.grootboek = grootboek;
    }
    public Kostenplaats getKostenplaats() {
        return kostenplaats;
    }
    public void setKostenplaats(Kostenplaats kostenplaats) {
        this.kostenplaats = kostenplaats;
    }
    public String getDatum() {
        return datum;
    }
    public void setDatum(String datum) {
        this.datum = datum;
    }
    public String getModifiedOn() {
        return modifiedOn;
    }
    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
    public Dagboek getDagboek() {
        return dagboek;
    }
    public void setDagboek(Dagboek dagboek) {
        this.dagboek = dagboek;
    }
    public String getOmschrijving() {
        return omschrijving;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    public double getDebet() {
        return debet;
    }
    public void setDebet(double debet) {
        this.debet = debet;
    }
    public double getCredit() {
        return credit;
    }
    public void setCredit(double credit) {
        this.credit = credit;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public List<Document> getDocuments() {
        return documents;
    }
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
    public String getBoekstuk() {
        return boekstuk;
    }
    public void setBoekstuk(String boekstuk) {
        this.boekstuk = boekstuk;
    }
    public String getFactuurNummer() {
        return factuurNummer;
    }
    public void setFactuurNummer(String factuurNummer) {
        this.factuurNummer = factuurNummer;
    }
    public Identifier getRelatiePublicIdentifier() {
        return relatiePublicIdentifier;
    }
    public void setRelatiePublicIdentifier(Identifier relatiePublicIdentifier) {
        this.relatiePublicIdentifier = relatiePublicIdentifier;
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
