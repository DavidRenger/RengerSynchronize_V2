package dev.shingi;

public class BoekingBoekingsRegel {
    private Identifier boekingId;
    private String omschrijving;
    private double debet;
    private double credit;

    public Identifier getBoekingId() {
        return boekingId;
    }
    public void setBoekingId(Identifier boekingId) {
        this.boekingId = boekingId;
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
}
