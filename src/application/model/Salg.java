package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Salg {

    private LocalDateTime salgsTidspunkt;
    private double samletPris;
    private int samletKlip;
    private RabatBeregning rabat;
    private HashMap<Produkt, Integer> produkter = new HashMap<>();


    public Salg(LocalDateTime salgsTidspunkt, double samletPris,int samletKlip, RabatBeregning rabat){
        this.salgsTidspunkt = salgsTidspunkt;
        this.samletPris = samletPris;
        this.samletKlip = samletKlip;
        this.rabat = rabat;
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

    public void addProdukt(Produkt produkt, int antal){
        if (!produkter.containsKey(produkt)){
            produkter.put(produkt,antal);
        }

    }

    public void removeProdukt(Produkt produkt){
        produkter.remove(produkt);
    }

    public HashMap<Produkt, Integer> getProdukter(){
        return produkter;
    }



}
