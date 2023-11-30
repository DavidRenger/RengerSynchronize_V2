package dev.shingi;

public class Adres {
    private String contactpersoon;
    private String straat;
    private String postcode;
    private String plaats;
    private Identifier land;

    public String getContactpersoon() {
        return contactpersoon;
    }
    public void setContactpersoon(String contactpersoon) {
        this.contactpersoon = contactpersoon;
    }
    public String getStraat() {
        return straat;
    }
    public void setStraat(String straat) {
        this.straat = straat;
    }
    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getPlaats() {
        return plaats;
    }
    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }
    public Identifier getLand() {
        return land;
    }
    public void setLand(Identifier land) {
        this.land = land;
    }
}

