package dev.shingi;

import java.util.List;
import java.util.UUID;

public class Bankboeking {
    private String modifiedOn;
    private String datum;
    private boolean markering;
    private String boekstuk;
    private boolean gewijzigdDoorAccountant;
    private String omschrijving;
    private List<GrootboekBoekingsRegel> grootboekBoekingsRegels;
    private List<BoekingBoekingsRegel> inkoopboekingBoekingsRegels;
    private List<BoekingBoekingsRegel> verkoopboekingBoekingsRegels;
    private List<BtwBoekingsRegel> btwBoekingsRegels;
    private double bedragUitgegeven;
    private double bedragOntvangen;
    private Identifier dagboek;
    private UUID id;
    private String uri;

    public String getModifiedOn() {
        return modifiedOn;
    }
    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
    public String getDatum() {
        return datum;
    }
    public void setDatum(String datum) {
        this.datum = datum;
    }
    public boolean isMarkering() {
        return markering;
    }
    public void setMarkering(boolean markering) {
        this.markering = markering;
    }
    public String getBoekstuk() {
        return boekstuk;
    }
    public void setBoekstuk(String boekstuk) {
        this.boekstuk = boekstuk;
    }
    public boolean isGewijzigdDoorAccountant() {
        return gewijzigdDoorAccountant;
    }
    public void setGewijzigdDoorAccountant(boolean gewijzigdDoorAccountant) {
        this.gewijzigdDoorAccountant = gewijzigdDoorAccountant;
    }
    public String getOmschrijving() {
        return omschrijving;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    public List<GrootboekBoekingsRegel> getGrootboekBoekingsRegels() {
        return grootboekBoekingsRegels;
    }
    public void setGrootboekBoekingsRegels(List<GrootboekBoekingsRegel> grootboekBoekingsRegels) {
        this.grootboekBoekingsRegels = grootboekBoekingsRegels;
    }
    public List<BoekingBoekingsRegel> getInkoopboekingBoekingsRegels() {
        return inkoopboekingBoekingsRegels;
    }
    public void setInkoopboekingBoekingsRegels(List<BoekingBoekingsRegel> inkoopboekingBoekingsRegels) {
        this.inkoopboekingBoekingsRegels = inkoopboekingBoekingsRegels;
    }
    public List<BoekingBoekingsRegel> getVerkoopboekingBoekingsRegels() {
        return verkoopboekingBoekingsRegels;
    }
    public void setVerkoopboekingBoekingsRegels(List<BoekingBoekingsRegel> verkoopboekingBoekingsRegels) {
        this.verkoopboekingBoekingsRegels = verkoopboekingBoekingsRegels;
    }
    public List<BtwBoekingsRegel> getBtwBoekingsRegels() {
        return btwBoekingsRegels;
    }
    public void setBtwBoekingsRegels(List<BtwBoekingsRegel> btwBoekingsRegels) {
        this.btwBoekingsRegels = btwBoekingsRegels;
    }
    public double getBedragUitgegeven() {
        return bedragUitgegeven;
    }
    public void setBedragUitgegeven(double bedragUitgegeven) {
        this.bedragUitgegeven = bedragUitgegeven;
    }
    public double getBedragOntvangen() {
        return bedragOntvangen;
    }
    public void setBedragOntvangen(double bedragOntvangen) {
        this.bedragOntvangen = bedragOntvangen;
    }
    public Identifier getDagboek() {
        return dagboek;
    }
    public void setDagboek(Identifier dagboek) {
        this.dagboek = dagboek;
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