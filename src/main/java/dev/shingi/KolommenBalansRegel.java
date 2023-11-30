package dev.shingi;

import java.util.List;

public class KolommenBalansRegel {

    private Identifier grootboekIdentifier;
    private String grootboekOmschrijving;
    private int grootboekNummer;
    private double verliesEnWinstDebet;
    private double verliesEnWinstCredit;
    private double balansDebet;
    private double balansCredit;
    private List<Identifier> rgsCode;

    @Override
    public String toString() {

        return grootboekNummer + " " + grootboekOmschrijving + "\n" + verliesEnWinstDebet + ", " + verliesEnWinstCredit + ", " + balansDebet + ", " + balansCredit;

        // Everything
        // return "KolommenBalansRegel [grootboekIdentifier=" + grootboekIdentifier + ", grootboekOmschrijving="
        //         + grootboekOmschrijving + ", grootboekNummer=" + grootboekNummer + ", verliesEnWinstDebet="
        //         + verliesEnWinstDebet + ", verliesEnWinstCredit=" + verliesEnWinstCredit + ", balansDebet="
        //         + balansDebet + ", balansCredit=" + balansCredit + ", rgsCode=" + rgsCode + "]";
    }

    public Identifier getGrootboekIdentifier() {
        return grootboekIdentifier;
    }

    public void setGrootboekIdentifier(Identifier grootboekIdentifier) {
        this.grootboekIdentifier = grootboekIdentifier;
    }

    public String getGrootboekOmschrijving() {
        return grootboekOmschrijving;
    }

    public void setGrootboekOmschrijving(String grootboekOmschrijving) {
        this.grootboekOmschrijving = grootboekOmschrijving;
    }

    public int getGrootboekNummer() {
        return grootboekNummer;
    }

    public void setGrootboekNummer(int grootboekNummer) {
        this.grootboekNummer = grootboekNummer;
    }

    public double getVerliesEnWinstDebet() {
        return verliesEnWinstDebet;
    }

    public void setVerliesEnWinstDebet(double verliesEnWinstDebet) {
        this.verliesEnWinstDebet = verliesEnWinstDebet;
    }

    public double getVerliesEnWinstCredit() {
        return verliesEnWinstCredit;
    }

    public void setVerliesEnWinstCredit(double verliesEnWinstCredit) {
        this.verliesEnWinstCredit = verliesEnWinstCredit;
    }

    public double getBalansDebet() {
        return balansDebet;
    }

    public void setBalansDebet(double balansDebet) {
        this.balansDebet = balansDebet;
    }

    public double getBalansCredit() {
        return balansCredit;
    }

    public void setBalansCredit(double balansCredit) {
        this.balansCredit = balansCredit;
    }

    public List<Identifier> getRgsCode() {
        return rgsCode;
    }

    public void setRgsCode(List<Identifier> rgsCode) {
        this.rgsCode = rgsCode;
    }
    
}

class KolommenBalansComparator implements java.util.Comparator<KolommenBalansRegel> {
    @Override
    public int compare(KolommenBalansRegel o1, KolommenBalansRegel o2) {
        return o1.getGrootboekNummer() - o2.getGrootboekNummer();
    }
}