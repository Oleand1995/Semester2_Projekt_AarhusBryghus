package application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Salg {

    private LocalDateTime salgsTidspunkt;
    private double samletPris;
    private int samletKlip;
    private RabatBeregning rabat;
    private ArrayList<Produkt> produkter = new ArrayList<Produkt>();


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

    public void addProdukt(Produkt produkt){
        if (!produkter.contains(produkt)){
            produkter.add(produkt);
        }

    }

    public void removeProdukt(Produkt produkt){
        produkter.remove(produkt);
    }

    public ArrayList<Produkt> getProdukter(){
        return produkter;
    }

    public int samletPris(){
        int sum = 0;
        for (Produkt p : produkter){
            sum += p.getPris();
        }
        return sum;
    }


}
