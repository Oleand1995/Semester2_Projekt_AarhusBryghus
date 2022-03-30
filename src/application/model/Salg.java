package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Salg {

    private LocalDateTime salgsTidspunkt;
    private double samletPris;
    private int samletKlip;
    private RabatBeregning rabat;
    private Prisliste prisliste;


    public Salg(LocalDateTime salgsTidspunkt, Prisliste prisliste){
        this.salgsTidspunkt = salgsTidspunkt;
        this.prisliste = prisliste;
    }

    public LocalDateTime getSalgsTidspunkt() {
        return salgsTidspunkt;
    }

    public double getSamletPris() {
        return samletPris;
    }

    public int getSamletKlip() {
        return samletKlip;
    }

    public void setPrisliste(Prisliste prisliste){
        this.prisliste = prisliste;
    }

    public Prisliste getPrisliste(){return this.prisliste;}

}