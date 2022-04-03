package application.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

public class Salg {

    private LocalDateTime salgsTidspunkt;
    private double samletPris;
    private int samletKlip;
    private ArrayList<OrdreLinje> ordrelinjer;


    public Salg(LocalDateTime salgsTidspunkt, ArrayList<OrdreLinje> ordrelinjer, double samletPris, int samletKlip){
        this.salgsTidspunkt = salgsTidspunkt;
        this.ordrelinjer = ordrelinjer;
        this.samletKlip = samletKlip;
        this.samletPris = samletPris;
    }

    public LocalDateTime getSalgsTidspunkt() {
        return salgsTidspunkt;
    }

    public void setSalgsTidspunkt(LocalDateTime salgsTidspunkt) {
        this.salgsTidspunkt = salgsTidspunkt;
    }

    public void setSamletPris(double samletPris) {
        this.samletPris = samletPris;
    }

    public int getSamletKlip() {
        return samletKlip;
    }

    public void setSamletKlip(int samletKlip) {
        this.samletKlip = samletKlip;
    }

    public ArrayList<OrdreLinje> getOrdrelinjer(){return new ArrayList<>(ordrelinjer);}



    public double getSamletPris(){
        double samletPris = 0;
        for (OrdreLinje o : ordrelinjer){
            samletPris += o.getPris().getPris() * o.getAntal();
        }
        return samletPris;
    }

    public String toString(){
        if (samletPris == 0){
            return "Salgstidspunkt: " + salgsTidspunkt.truncatedTo(ChronoUnit.MINUTES) + " | Pris: " + samletKlip + " klip";
        }
        else{
            return "Salgstidspunkt: " + salgsTidspunkt.truncatedTo(ChronoUnit.MINUTES) + " | Pris: " + samletPris + ",-";
        }
    }

}