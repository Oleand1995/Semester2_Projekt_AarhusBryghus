package application.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Salg {

    private LocalDateTime salgsTidspunkt;
    private double samletPris;
    private int samletKlip;
    private ArrayList<Produkt> produkter;

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


    public void addProduktTilSalg(Produkt produkt){
        produkter.add(produkt);
    }

    public void removeProduktFraSalg(Produkt produkt){
        produkter.remove(produkt);
    }

}
