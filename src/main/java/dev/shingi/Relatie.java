package dev.shingi;

import java.util.List;
import java.util.UUID;

public class Relatie {
    private List<String> relatiesoort; // Klant of Leverancier
    private String modifiedOn;
    private int relatiecode;
    private String naam;
    private Adres vestigingsAdres;
    private Adres correspondentieAdres;
    private String telefoon;
    private String mobieleTelefoon;
    private String email;
    private String btwNummer;
    private int factuurkorting;
    private int krediettermijn;
    private boolean bankieren;
    private boolean nonactief;
    private int kredietLimiet;
    private String memo;
    private String kvkNummer;
    private String oin;
    private String websiteUrl;
    private String aanmaningsoort;
    private EmailVersturen offerteEmailVersturen;
    private EmailVersturen bevestigingsEmailVersturen;
    private EmailVersturen factuurEmailVersturen;
    private EmailVersturen aanmaningEmailVersturen;
    private EmailVersturen offerteAanvraagEmailVersturen;
    private EmailVersturen bestellingEmailVersturen;
    private boolean ublBestandAlsBijlage;
    private String iban;
    private String bic;
    private String incassoSoort;
    private Relatie factuurRelatie;
    private String inkoopBoekingenUri;
    private String verkoopBoekingenUri;
    private List<Document> documents;
    private UUID id;
    private String uri;
    private List<ExtraVeldKlant> extraVeldenKlant;

    public List<String> getRelatiesoort() {
        return relatiesoort;
    }
    public void setRelatiesoort(List<String> relatiesoort) {
        this.relatiesoort = relatiesoort;
    }
    public String getModifiedOn() {
        return modifiedOn;
    }
    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
    public int getRelatiecode() {
        return relatiecode;
    }
    public void setRelatiecode(int relatiecode) {
        this.relatiecode = relatiecode;
    }
    public String getNaam() {
        return naam;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public Adres getVestigingsAdres() {
        return vestigingsAdres;
    }
    public void setVestigingsAdres(Adres vestigingsAdres) {
        this.vestigingsAdres = vestigingsAdres;
    }
    public Adres getCorrespondentieAdres() {
        return correspondentieAdres;
    }
    public void setCorrespondentieAdres(Adres correspondentieAdres) {
        this.correspondentieAdres = correspondentieAdres;
    }
    public String getTelefoon() {
        return telefoon;
    }
    public void setTelefoon(String telefoon) {
        this.telefoon = telefoon;
    }
    public String getMobieleTelefoon() {
        return mobieleTelefoon;
    }
    public void setMobieleTelefoon(String mobieleTelefoon) {
        this.mobieleTelefoon = mobieleTelefoon;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getBtwNummer() {
        return btwNummer;
    }
    public void setBtwNummer(String btwNummer) {
        this.btwNummer = btwNummer;
    }
    public int getFactuurkorting() {
        return factuurkorting;
    }
    public void setFactuurkorting(int factuurkorting) {
        this.factuurkorting = factuurkorting;
    }
    public int getKrediettermijn() {
        return krediettermijn;
    }
    public void setKrediettermijn(int krediettermijn) {
        this.krediettermijn = krediettermijn;
    }
    public boolean isBankieren() {
        return bankieren;
    }
    public void setBankieren(boolean bankieren) {
        this.bankieren = bankieren;
    }
    public boolean isNonactief() {
        return nonactief;
    }
    public void setNonactief(boolean nonactief) {
        this.nonactief = nonactief;
    }
    public int getKredietLimiet() {
        return kredietLimiet;
    }
    public void setKredietLimiet(int kredietLimiet) {
        this.kredietLimiet = kredietLimiet;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getKvkNummer() {
        return kvkNummer;
    }
    public void setKvkNummer(String kvkNummer) {
        this.kvkNummer = kvkNummer;
    }
    public String getOin() {
        return oin;
    }
    public void setOin(String oin) {
        this.oin = oin;
    }
    public String getWebsiteUrl() {
        return websiteUrl;
    }
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
    public String getAanmaningsoort() {
        return aanmaningsoort;
    }
    public void setAanmaningsoort(String aanmaningsoort) {
        this.aanmaningsoort = aanmaningsoort;
    }
    public EmailVersturen getOfferteEmailVersturen() {
        return offerteEmailVersturen;
    }
    public void setOfferteEmailVersturen(EmailVersturen offerteEmailVersturen) {
        this.offerteEmailVersturen = offerteEmailVersturen;
    }
    public EmailVersturen getBevestigingsEmailVersturen() {
        return bevestigingsEmailVersturen;
    }
    public void setBevestigingsEmailVersturen(EmailVersturen bevestigingsEmailVersturen) {
        this.bevestigingsEmailVersturen = bevestigingsEmailVersturen;
    }
    public EmailVersturen getFactuurEmailVersturen() {
        return factuurEmailVersturen;
    }
    public void setFactuurEmailVersturen(EmailVersturen factuurEmailVersturen) {
        this.factuurEmailVersturen = factuurEmailVersturen;
    }
    public EmailVersturen getAanmaningEmailVersturen() {
        return aanmaningEmailVersturen;
    }
    public void setAanmaningEmailVersturen(EmailVersturen aanmaningEmailVersturen) {
        this.aanmaningEmailVersturen = aanmaningEmailVersturen;
    }
    public EmailVersturen getOfferteAanvraagEmailVersturen() {
        return offerteAanvraagEmailVersturen;
    }
    public void setOfferteAanvraagEmailVersturen(EmailVersturen offerteAanvraagEmailVersturen) {
        this.offerteAanvraagEmailVersturen = offerteAanvraagEmailVersturen;
    }
    public EmailVersturen getBestellingEmailVersturen() {
        return bestellingEmailVersturen;
    }
    public void setBestellingEmailVersturen(EmailVersturen bestellingEmailVersturen) {
        this.bestellingEmailVersturen = bestellingEmailVersturen;
    }
    public boolean isUblBestandAlsBijlage() {
        return ublBestandAlsBijlage;
    }
    public void setUblBestandAlsBijlage(boolean ublBestandAlsBijlage) {
        this.ublBestandAlsBijlage = ublBestandAlsBijlage;
    }
    public String getIban() {
        return iban;
    }
    public void setIban(String iban) {
        this.iban = iban;
    }
    public String getBic() {
        return bic;
    }
    public void setBic(String bic) {
        this.bic = bic;
    }
    public String getIncassoSoort() {
        return incassoSoort;
    }
    public void setIncassoSoort(String incassoSoort) {
        this.incassoSoort = incassoSoort;
    }
    public Relatie getFactuurRelatie() {
        return factuurRelatie;
    }
    public void setFactuurRelatie(Relatie factuurRelatie) {
        this.factuurRelatie = factuurRelatie;
    }
    public String getInkoopBoekingenUri() {
        return inkoopBoekingenUri;
    }
    public void setInkoopBoekingenUri(String inkoopBoekingenUri) {
        this.inkoopBoekingenUri = inkoopBoekingenUri;
    }
    public String getVerkoopBoekingenUri() {
        return verkoopBoekingenUri;
    }
    public void setVerkoopBoekingenUri(String verkoopBoekingenUri) {
        this.verkoopBoekingenUri = verkoopBoekingenUri;
    }
    public List<Document> getDocuments() {
        return documents;
    }
    public void setDocuments(List<Document> documents) {
        this.documents = documents;
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
    public List<ExtraVeldKlant> getExtraVeldenKlant() {
        return extraVeldenKlant;
    }
    public void setExtraVeldenKlant(List<ExtraVeldKlant> extraVeldenKlant) {
        this.extraVeldenKlant = extraVeldenKlant;
    }

    
}
