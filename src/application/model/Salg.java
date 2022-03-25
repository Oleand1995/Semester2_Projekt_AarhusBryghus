package application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Salg {

    private LocalDateTime salgsTidspunkt;
    private double samletPris;
    private int samletKlip;

    public Salg(LocalDateTime salgsTidspunkt, double samletPris,int samletKlip){
        this.salgsTidspunkt = salgsTidspunkt;
        this.samletPris = samletPris;
        this.samletKlip = samletKlip;
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



}
