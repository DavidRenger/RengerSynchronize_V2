package dev.shingi.Endpoints.Regels;

public class KleineOndernemersregeling {
    private boolean isKleineOndernemersRegelingActief;
    private int maximaalBedragVolledigeVerrekening;
    private int maximaalBedragKleineOndernemersRegeling;

    
    public boolean isKleineOndernemersRegelingActief() {
        return isKleineOndernemersRegelingActief;
    }
    public void setKleineOndernemersRegelingActief(boolean isKleineOndernemersRegelingActief) {
        this.isKleineOndernemersRegelingActief = isKleineOndernemersRegelingActief;
    }
    public int getMaximaalBedragVolledigeVerrekening() {
        return maximaalBedragVolledigeVerrekening;
    }
    public void setMaximaalBedragVolledigeVerrekening(int maximaalBedragVolledigeVerrekening) {
        this.maximaalBedragVolledigeVerrekening = maximaalBedragVolledigeVerrekening;
    }
    public int getMaximaalBedragKleineOndernemersRegeling() {
        return maximaalBedragKleineOndernemersRegeling;
    }
    public void setMaximaalBedragKleineOndernemersRegeling(int maximaalBedragKleineOndernemersRegeling) {
        this.maximaalBedragKleineOndernemersRegeling = maximaalBedragKleineOndernemersRegeling;
    }

    
}
