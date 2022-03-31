package application.model;

import java.util.ArrayList;

public class Pris {

    private ArrayList<Produkt> produkter = new ArrayList<>();
    private int pris;

    public Pris(int pris){
        this.pris = pris;
    }

    public void addProdukt(Produkt produkt){
        if (!produkter.contains(produkt)){
            produkter.add(produkt);
        }
    }

    public void removeProdukt(Produkt produkt){
        produkter.remove(produkt);
    }

    public ArrayList<Produkt> getProdukter(){return new ArrayList<>(produkter);}


}
