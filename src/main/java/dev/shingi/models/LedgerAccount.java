package dev.shingi.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class LedgerAccount implements Comparable<LedgerAccount> {
    
    // Information from HTTP request
    String omschrijving;
    Boolean kostenplaatsVerplicht;
    String rekeningCode;
    Boolean nonactief;
    Integer nummer;
    String grootboekfunctie;
    String grootboekRubriek;
    List<Map<String, Object>> rgsCodes;
    List<String> btwSoort; // Geeft een enkele of combinatie van 'Geen' 'Overig' 'Hoog' 'Laag'
    String vatRateCode;
    UUID id;
    String uri;

    double uniformityPercentage;
    List<Customer> customers;

    public LedgerAccount(String omschrijving, Boolean kostenplaatsVerplicht, String rekeningCode, Boolean nonactief,
            Integer nummer, String grootboekfunctie, String grootboekRubriek, List<Map<String, Object>> rgsCodes,
            List<String> btwSoort, String vatRateCode, UUID id, String uri) {
        this.omschrijving = omschrijving;
        this.kostenplaatsVerplicht = kostenplaatsVerplicht;
        this.rekeningCode = rekeningCode;
        this.nonactief = nonactief;
        this.nummer = nummer;
        this.grootboekfunctie = grootboekfunctie;
        this.grootboekRubriek = grootboekRubriek;
        this.rgsCodes = rgsCodes;
        this.btwSoort = btwSoort;
        this.vatRateCode = vatRateCode;
        this.id = id;
        this.uri = uri;

        uniformityPercentage = 0;
        customers = new ArrayList<>();
    }

    public LedgerAccount(String omschrijving, int nummer) {
        this.omschrijving = omschrijving;
        this.nummer = nummer;

        uniformityPercentage = 0;
        customers = new ArrayList<>();
    }

    public LedgerAccount(String omschrijving) {
        this.omschrijving = omschrijving;

        uniformityPercentage = 0;
        customers = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LedgerAccount that = (LedgerAccount) o;
        return Objects.equals(nummer, that.nummer) && Objects.equals(omschrijving, that.omschrijving);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nummer, omschrijving);
    }
    
    @Override
    public int compareTo(LedgerAccount other) {
        return Integer.compare(this.nummer, other.nummer);
    }
    
    @Override
    public String toString() {
        return omschrijving;
    }

    public String getOmschrijving() {
        return omschrijving;
    }
    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    public Boolean getKostenplaatsVerplicht() {
        return kostenplaatsVerplicht;
    }
    public void setKostenplaatsVerplicht(Boolean kostenplaatsVerplicht) {
        this.kostenplaatsVerplicht = kostenplaatsVerplicht;
    }
    public String getRekeningCode() {
        return rekeningCode;
    }
    public void setRekeningCode(String rekeningCode) {
        this.rekeningCode = rekeningCode;
    }
    public Boolean getNonactief() {
        return nonactief;
    }
    public void setNonactief(Boolean nonactief) {
        this.nonactief = nonactief;
    }
    public Integer getNummer() {
        return nummer;
    }
    public void setNummer(Integer nummer) {
        this.nummer = nummer;
    }
    public String getGrootboekfunctie() {
        return grootboekfunctie;
    }
    public void setGrootboekfunctie(String grootboekfunctie) {
        this.grootboekfunctie = grootboekfunctie;
    }
    public String getGrootboekRubriek() {
        return grootboekRubriek;
    }
    public void setGrootboekRubriek(String grootboekRubriek) {
        this.grootboekRubriek = grootboekRubriek;
    }
    public List<Map<String, Object>> getRgsCodes() {
        return rgsCodes;
    }
    public void setRgsCodes(List<Map<String, Object>> rgsCodes) {
        this.rgsCodes = rgsCodes;
    }
    public List<String> getBtwSoort() {
        return btwSoort;
    }
    public void setBtwSoort(List<String> btwSoort) {
        this.btwSoort = btwSoort;
    }
    public String getVatRateCode() {
        return vatRateCode;
    }
    public void setVatRateCode(String vatRateCode) {
        this.vatRateCode = vatRateCode;
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

    public void setUniformityPercentage(double i) {
        this.uniformityPercentage = i;
    }

    public double getUniformityPercentage() {
        return uniformityPercentage;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}