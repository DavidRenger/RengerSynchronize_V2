package dev.shingi.Endpoints.Regels;

import dev.shingi.Endpoints.Models.Identifier;

public class GrootboekBoekingsRegel {
    private String omschrijving;
    private Identifier grootboek;
    private Identifier kostenplaats;
    private double debet;
    private double credit;
    private String btwSoort;

    public String getOmschrijving() {
        return omschrijving;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    public Identifier getGrootboek() {
        return grootboek;
    }
    public void setGrootboek(Identifier grootboek) {
        this.grootboek = grootboek;
    }
    public Identifier getKostenplaats() {
        return kostenplaats;
    }
    public void setKostenplaats(Identifier kostenplaats) {
        this.kostenplaats = kostenplaats;
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
    public String getBtwSoort() {
        return btwSoort;
    }
    public void setBtwSoort(String btwSoort) {
        this.btwSoort = btwSoort;
    }
}