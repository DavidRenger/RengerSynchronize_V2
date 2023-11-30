package dev.shingi.endpoints.models;

import java.util.List;
import java.util.UUID;

import dev.shingi.endpoints.regels.KleineOndernemersregeling;

public class Administratie {
    private UUID administratieIdentifier;
    private String administratieNaam;
    private String bedrijfsnaam;
    private String contactpersoon;
    private String adres;
    private String postcode;
    private String plaats;
    private String telefoon;
    private String mobieleTelefoon;
    private String fax;
    private String bankrekeningnummer;
    private String iban;
    private String bic;
    private String rechtsvorm;
    private String btwNummer;
    private String btwIdentificatieNummer;
    private String kvKNummer;
    private String email;
    private String website;
    private String vrijeTekst1;
    private String vrijeTekst2;
    private String vrijeTekst3;
    private String vrijeTekst4;
    private int huidigBoekjaar;
    private int beginmaandFiscaleBoekjaar;
    private String btwAangiftePeriodeSoort;
    private String icpAangiftePeriodeSoort;
    private String btwNummerFiscaleEenheid;
    private boolean tussentijdseSuppletiesBerekenen;
    private String mapUBLBestanden;
    private int btwPercentageAangifteKredietbeperking;
    private String markeergedragInlezenBankafschriften;
    private boolean voorkeurenTijdensBoeken;
    private int aantalVoorloopnullenGrootboekrekeningen;
    private KleineOndernemersregeling kleineOndernemersregeling;
    private int volgendFactuurnummer;
    private int volgendVerkoopordernummer;
    private int volgendContantbonnummer;
    private int volgendInkoopordernummer;
    private boolean voorraadcontroleOrderinvoer;
    private boolean abonnementOvernemen;
    private boolean kolomGeleverdAutomatischVullen;
    private boolean voorraadkolommenTonenInInkoop;
    private boolean backorderGebruiken;
    private boolean deelleveringOrdersDefaultAan;
    private boolean factuurAlsBijlageVerkoopboeking;
    private String tekstregelsOvernemenNaarBackorder;
    private String regelkortingVerkooporder;
    private int drempelbedragVerkooporderbeheer;
    private int drempelbedragVerkooporderbeheerMaxDagenUitstel;
    private boolean verkoopprijsArtikelbestandExclusiefBtw;
    private boolean inkoopprijsArtikelbestandExclusiefBtw;
    private String artikelcodeSoort;
    private int artikelcodeMaxLengte;
    private String begindatumVoorraadtelling;
    private boolean voorraadTonenInZoekvenster;
    private int aantalDecimalenArtikelprijzen;
    private int aantalDecimalenArtikelaantallen;
    private String verkooporderVoorraadVanafNiveau;
    private String voorraadSysteem;
    private String momentVoorraadBijwerken;
    private Identifier rekeningTeOntvangenInkoopfacturen;
    private Identifier dagboekVoorraadverschillen;
    private Identifier buitenlandseBtwGrootboek;
    private boolean factureerBuitenlandsBtw;
    private List<BtwRange> factureerBuitenlandsBtwRanges;

    // Getters and Setters

    public UUID getAdministratieIdentifier() {
        return administratieIdentifier;
    }
    public void setAdministratieIdentifier(UUID administratieIdentifier) {
        this.administratieIdentifier = administratieIdentifier;
    }
    public String getAdministratieNaam() {
        return administratieNaam;
    }
    public void setAdministratieNaam(String administratieNaam) {
        this.administratieNaam = administratieNaam;
    }
    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }
    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
    }
    public String getContactpersoon() {
        return contactpersoon;
    }
    public void setContactpersoon(String contactpersoon) {
        this.contactpersoon = contactpersoon;
    }
    public String getAdres() {
        return adres;
    }
    public void setAdres(String adres) {
        this.adres = adres;
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
    public String getFax() {
        return fax;
    }
    public void setFax(String fax) {
        this.fax = fax;
    }
    public String getBankrekeningnummer() {
        return bankrekeningnummer;
    }
    public void setBankrekeningnummer(String bankrekeningnummer) {
        this.bankrekeningnummer = bankrekeningnummer;
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
    public String getRechtsvorm() {
        return rechtsvorm;
    }
    public void setRechtsvorm(String rechtsvorm) {
        this.rechtsvorm = rechtsvorm;
    }
    public String getBtwNummer() {
        return btwNummer;
    }
    public void setBtwNummer(String btwNummer) {
        this.btwNummer = btwNummer;
    }
    public String getBtwIdentificatieNummer() {
        return btwIdentificatieNummer;
    }
    public void setBtwIdentificatieNummer(String btwIdentificatieNummer) {
        this.btwIdentificatieNummer = btwIdentificatieNummer;
    }
    public String getKvKNummer() {
        return kvKNummer;
    }
    public void setKvKNummer(String kvKNummer) {
        this.kvKNummer = kvKNummer;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public String getVrijeTekst1() {
        return vrijeTekst1;
    }
    public void setVrijeTekst1(String vrijeTekst1) {
        this.vrijeTekst1 = vrijeTekst1;
    }
    public String getVrijeTekst2() {
        return vrijeTekst2;
    }
    public void setVrijeTekst2(String vrijeTekst2) {
        this.vrijeTekst2 = vrijeTekst2;
    }
    public String getVrijeTekst3() {
        return vrijeTekst3;
    }
    public void setVrijeTekst3(String vrijeTekst3) {
        this.vrijeTekst3 = vrijeTekst3;
    }
    public String getVrijeTekst4() {
        return vrijeTekst4;
    }
    public void setVrijeTekst4(String vrijeTekst4) {
        this.vrijeTekst4 = vrijeTekst4;
    }
    public int getHuidigBoekjaar() {
        return huidigBoekjaar;
    }
    public void setHuidigBoekjaar(int huidigBoekjaar) {
        this.huidigBoekjaar = huidigBoekjaar;
    }
    public int getBeginmaandFiscaleBoekjaar() {
        return beginmaandFiscaleBoekjaar;
    }
    public void setBeginmaandFiscaleBoekjaar(int beginmaandFiscaleBoekjaar) {
        this.beginmaandFiscaleBoekjaar = beginmaandFiscaleBoekjaar;
    }
    public String getBtwAangiftePeriodeSoort() {
        return btwAangiftePeriodeSoort;
    }
    public void setBtwAangiftePeriodeSoort(String btwAangiftePeriodeSoort) {
        this.btwAangiftePeriodeSoort = btwAangiftePeriodeSoort;
    }
    public String getIcpAangiftePeriodeSoort() {
        return icpAangiftePeriodeSoort;
    }
    public void setIcpAangiftePeriodeSoort(String icpAangiftePeriodeSoort) {
        this.icpAangiftePeriodeSoort = icpAangiftePeriodeSoort;
    }
    public String getBtwNummerFiscaleEenheid() {
        return btwNummerFiscaleEenheid;
    }
    public void setBtwNummerFiscaleEenheid(String btwNummerFiscaleEenheid) {
        this.btwNummerFiscaleEenheid = btwNummerFiscaleEenheid;
    }
    public boolean isTussentijdseSuppletiesBerekenen() {
        return tussentijdseSuppletiesBerekenen;
    }
    public void setTussentijdseSuppletiesBerekenen(boolean tussentijdseSuppletiesBerekenen) {
        this.tussentijdseSuppletiesBerekenen = tussentijdseSuppletiesBerekenen;
    }
    public String getMapUBLBestanden() {
        return mapUBLBestanden;
    }
    public void setMapUBLBestanden(String mapUBLBestanden) {
        this.mapUBLBestanden = mapUBLBestanden;
    }
    public int getBtwPercentageAangifteKredietbeperking() {
        return btwPercentageAangifteKredietbeperking;
    }
    public void setBtwPercentageAangifteKredietbeperking(int btwPercentageAangifteKredietbeperking) {
        this.btwPercentageAangifteKredietbeperking = btwPercentageAangifteKredietbeperking;
    }
    public String getMarkeergedragInlezenBankafschriften() {
        return markeergedragInlezenBankafschriften;
    }
    public void setMarkeergedragInlezenBankafschriften(String markeergedragInlezenBankafschriften) {
        this.markeergedragInlezenBankafschriften = markeergedragInlezenBankafschriften;
    }
    public boolean isVoorkeurenTijdensBoeken() {
        return voorkeurenTijdensBoeken;
    }
    public void setVoorkeurenTijdensBoeken(boolean voorkeurenTijdensBoeken) {
        this.voorkeurenTijdensBoeken = voorkeurenTijdensBoeken;
    }
    public int getAantalVoorloopnullenGrootboekrekeningen() {
        return aantalVoorloopnullenGrootboekrekeningen;
    }
    public void setAantalVoorloopnullenGrootboekrekeningen(int aantalVoorloopnullenGrootboekrekeningen) {
        this.aantalVoorloopnullenGrootboekrekeningen = aantalVoorloopnullenGrootboekrekeningen;
    }
    public KleineOndernemersregeling getKleineOndernemersregeling() {
        return kleineOndernemersregeling;
    }
    public void setKleineOndernemersregeling(KleineOndernemersregeling kleineOndernemersregeling) {
        this.kleineOndernemersregeling = kleineOndernemersregeling;
    }
    public int getVolgendFactuurnummer() {
        return volgendFactuurnummer;
    }
    public void setVolgendFactuurnummer(int volgendFactuurnummer) {
        this.volgendFactuurnummer = volgendFactuurnummer;
    }
    public int getVolgendVerkoopordernummer() {
        return volgendVerkoopordernummer;
    }
    public void setVolgendVerkoopordernummer(int volgendVerkoopordernummer) {
        this.volgendVerkoopordernummer = volgendVerkoopordernummer;
    }
    public int getVolgendContantbonnummer() {
        return volgendContantbonnummer;
    }
    public void setVolgendContantbonnummer(int volgendContantbonnummer) {
        this.volgendContantbonnummer = volgendContantbonnummer;
    }
    public int getVolgendInkoopordernummer() {
        return volgendInkoopordernummer;
    }
    public void setVolgendInkoopordernummer(int volgendInkoopordernummer) {
        this.volgendInkoopordernummer = volgendInkoopordernummer;
    }
    public boolean isVoorraadcontroleOrderinvoer() {
        return voorraadcontroleOrderinvoer;
    }
    public void setVoorraadcontroleOrderinvoer(boolean voorraadcontroleOrderinvoer) {
        this.voorraadcontroleOrderinvoer = voorraadcontroleOrderinvoer;
    }
    public boolean isAbonnementOvernemen() {
        return abonnementOvernemen;
    }
    public void setAbonnementOvernemen(boolean abonnementOvernemen) {
        this.abonnementOvernemen = abonnementOvernemen;
    }
    public boolean isKolomGeleverdAutomatischVullen() {
        return kolomGeleverdAutomatischVullen;
    }
    public void setKolomGeleverdAutomatischVullen(boolean kolomGeleverdAutomatischVullen) {
        this.kolomGeleverdAutomatischVullen = kolomGeleverdAutomatischVullen;
    }
    public boolean isVoorraadkolommenTonenInInkoop() {
        return voorraadkolommenTonenInInkoop;
    }
    public void setVoorraadkolommenTonenInInkoop(boolean voorraadkolommenTonenInInkoop) {
        this.voorraadkolommenTonenInInkoop = voorraadkolommenTonenInInkoop;
    }
    public boolean isBackorderGebruiken() {
        return backorderGebruiken;
    }
    public void setBackorderGebruiken(boolean backorderGebruiken) {
        this.backorderGebruiken = backorderGebruiken;
    }
    public boolean isDeelleveringOrdersDefaultAan() {
        return deelleveringOrdersDefaultAan;
    }
    public void setDeelleveringOrdersDefaultAan(boolean deelleveringOrdersDefaultAan) {
        this.deelleveringOrdersDefaultAan = deelleveringOrdersDefaultAan;
    }
    public boolean isFactuurAlsBijlageVerkoopboeking() {
        return factuurAlsBijlageVerkoopboeking;
    }
    public void setFactuurAlsBijlageVerkoopboeking(boolean factuurAlsBijlageVerkoopboeking) {
        this.factuurAlsBijlageVerkoopboeking = factuurAlsBijlageVerkoopboeking;
    }
    public String getTekstregelsOvernemenNaarBackorder() {
        return tekstregelsOvernemenNaarBackorder;
    }
    public void setTekstregelsOvernemenNaarBackorder(String tekstregelsOvernemenNaarBackorder) {
        this.tekstregelsOvernemenNaarBackorder = tekstregelsOvernemenNaarBackorder;
    }
    public String getRegelkortingVerkooporder() {
        return regelkortingVerkooporder;
    }
    public void setRegelkortingVerkooporder(String regelkortingVerkooporder) {
        this.regelkortingVerkooporder = regelkortingVerkooporder;
    }
    public int getDrempelbedragVerkooporderbeheer() {
        return drempelbedragVerkooporderbeheer;
    }
    public void setDrempelbedragVerkooporderbeheer(int drempelbedragVerkooporderbeheer) {
        this.drempelbedragVerkooporderbeheer = drempelbedragVerkooporderbeheer;
    }
    public int getDrempelbedragVerkooporderbeheerMaxDagenUitstel() {
        return drempelbedragVerkooporderbeheerMaxDagenUitstel;
    }
    public void setDrempelbedragVerkooporderbeheerMaxDagenUitstel(int drempelbedragVerkooporderbeheerMaxDagenUitstel) {
        this.drempelbedragVerkooporderbeheerMaxDagenUitstel = drempelbedragVerkooporderbeheerMaxDagenUitstel;
    }
    public boolean isVerkoopprijsArtikelbestandExclusiefBtw() {
        return verkoopprijsArtikelbestandExclusiefBtw;
    }
    public void setVerkoopprijsArtikelbestandExclusiefBtw(boolean verkoopprijsArtikelbestandExclusiefBtw) {
        this.verkoopprijsArtikelbestandExclusiefBtw = verkoopprijsArtikelbestandExclusiefBtw;
    }
    public boolean isInkoopprijsArtikelbestandExclusiefBtw() {
        return inkoopprijsArtikelbestandExclusiefBtw;
    }
    public void setInkoopprijsArtikelbestandExclusiefBtw(boolean inkoopprijsArtikelbestandExclusiefBtw) {
        this.inkoopprijsArtikelbestandExclusiefBtw = inkoopprijsArtikelbestandExclusiefBtw;
    }
    public String getArtikelcodeSoort() {
        return artikelcodeSoort;
    }
    public void setArtikelcodeSoort(String artikelcodeSoort) {
        this.artikelcodeSoort = artikelcodeSoort;
    }
    public int getArtikelcodeMaxLengte() {
        return artikelcodeMaxLengte;
    }
    public void setArtikelcodeMaxLengte(int artikelcodeMaxLengte) {
        this.artikelcodeMaxLengte = artikelcodeMaxLengte;
    }
    public String getBegindatumVoorraadtelling() {
        return begindatumVoorraadtelling;
    }
    public void setBegindatumVoorraadtelling(String begindatumVoorraadtelling) {
        this.begindatumVoorraadtelling = begindatumVoorraadtelling;
    }
    public boolean isVoorraadTonenInZoekvenster() {
        return voorraadTonenInZoekvenster;
    }
    public void setVoorraadTonenInZoekvenster(boolean voorraadTonenInZoekvenster) {
        this.voorraadTonenInZoekvenster = voorraadTonenInZoekvenster;
    }
    public int getAantalDecimalenArtikelprijzen() {
        return aantalDecimalenArtikelprijzen;
    }
    public void setAantalDecimalenArtikelprijzen(int aantalDecimalenArtikelprijzen) {
        this.aantalDecimalenArtikelprijzen = aantalDecimalenArtikelprijzen;
    }
    public int getAantalDecimalenArtikelaantallen() {
        return aantalDecimalenArtikelaantallen;
    }
    public void setAantalDecimalenArtikelaantallen(int aantalDecimalenArtikelaantallen) {
        this.aantalDecimalenArtikelaantallen = aantalDecimalenArtikelaantallen;
    }
    public String getVerkooporderVoorraadVanafNiveau() {
        return verkooporderVoorraadVanafNiveau;
    }
    public void setVerkooporderVoorraadVanafNiveau(String verkooporderVoorraadVanafNiveau) {
        this.verkooporderVoorraadVanafNiveau = verkooporderVoorraadVanafNiveau;
    }
    public String getVoorraadSysteem() {
        return voorraadSysteem;
    }
    public void setVoorraadSysteem(String voorraadSysteem) {
        this.voorraadSysteem = voorraadSysteem;
    }
    public String getMomentVoorraadBijwerken() {
        return momentVoorraadBijwerken;
    }
    public void setMomentVoorraadBijwerken(String momentVoorraadBijwerken) {
        this.momentVoorraadBijwerken = momentVoorraadBijwerken;
    }
    public Identifier getRekeningTeOntvangenInkoopfacturen() {
        return rekeningTeOntvangenInkoopfacturen;
    }
    public void setRekeningTeOntvangenInkoopfacturen(Identifier rekeningTeOntvangenInkoopfacturen) {
        this.rekeningTeOntvangenInkoopfacturen = rekeningTeOntvangenInkoopfacturen;
    }
    public Identifier getDagboekVoorraadverschillen() {
        return dagboekVoorraadverschillen;
    }
    public void setDagboekVoorraadverschillen(Identifier dagboekVoorraadverschillen) {
        this.dagboekVoorraadverschillen = dagboekVoorraadverschillen;
    }
    public Identifier getBuitenlandseBtwGrootboek() {
        return buitenlandseBtwGrootboek;
    }
    public void setBuitenlandseBtwGrootboek(Identifier buitenlandseBtwGrootboek) {
        this.buitenlandseBtwGrootboek = buitenlandseBtwGrootboek;
    }
    public boolean isFactureerBuitenlandsBtw() {
        return factureerBuitenlandsBtw;
    }
    public void setFactureerBuitenlandsBtw(boolean factureerBuitenlandsBtw) {
        this.factureerBuitenlandsBtw = factureerBuitenlandsBtw;
    }
    public List<BtwRange> getFactureerBuitenlandsBtwRanges() {
        return factureerBuitenlandsBtwRanges;
    }
    public void setFactureerBuitenlandsBtwRanges(List<BtwRange> factureerBuitenlandsBtwRanges) {
        this.factureerBuitenlandsBtwRanges = factureerBuitenlandsBtwRanges;
    }
}