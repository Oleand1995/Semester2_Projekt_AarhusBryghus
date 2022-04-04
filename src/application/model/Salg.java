package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Salg {

    private LocalDateTime salgsTidspunkt;
    private double samletPris;
    private int samletKlip;
    private ArrayList<OrdreLinje> ordrelinjer;
    private RabatBeregning rabat;


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

    public void setRabat(RabatBeregning rabat){
        this.rabat = rabat;
    }


    public double getSamletPris(){
        double samletPris = 0;
        for (OrdreLinje o : ordrelinjer){
            samletPris += o.getPris().getPris() * o.getAntal();
        }
        if (rabat != null){
            samletPris = rabat.getRabat(samletPris);
        }
        return samletPris;
    }

    public String toString(){return ordrelinjer + " | Total: " + samletPris + ",-";}



}