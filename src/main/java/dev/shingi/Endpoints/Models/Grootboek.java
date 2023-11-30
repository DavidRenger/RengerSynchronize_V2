
package dev.shingi.Endpoints.Models;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Grootboek implements Comparable<Grootboek> {

    // Information from HTTP request
    String modifiedOn;
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

    @Override
    public int compareTo(Grootboek other) {
        return Integer.compare(this.nummer, other.nummer);
    }
    
    @Override
    public String toString() {

        return nummer + " " + omschrijving;

        // Everything
        // return "Grootboek [modifiedOn=" + modifiedOn + ",\ndescription=" + omschrijving + ",\nkostenplaatsVerplicht="
        //         + kostenplaatsVerplicht + ",\nrekeningCode=" + rekeningCode + ",\nnonactief=" + nonactief + ",\nnummer="
        //         + nummer + ",\ngrootboekfunctie=" + grootboekfunctie + ",\ngrootboekRubriek=" + grootboekRubriek
        //         + ",\nrgsCodes=" + rgsCodes + ",\nbtwSoort=" + btwSoort + ",\nvatRateCode=" + vatRateCode + ",\nid=" + id
        //         + ",\nuri=" + uri + "]";
    }
    
    public String getModifiedOn() {
        return modifiedOn;
    }
    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
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
    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
}