package dev.shingi.Endpoints.Regels;

public class BtwBoekingsRegel {
    private double debet;
    private double credit;
    private String type;
    private String tarief;

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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTarief() {
        return tarief;
    }
    public void setTarief(String tarief) {
        this.tarief = tarief;
    }
}
