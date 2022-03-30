package application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prisliste {

    private String navn;
    private HashMap<Produkt, Double> priser = new HashMap<>();

    public Prisliste(String navn){
        this.navn = navn;
    }

    public String getNavn(){return this.navn;}

    public void setNavn(String navn){
        this.navn = navn;
    }

    public void addPris(Produkt produkt, double pris){
        priser.put(produkt, pris);
    }

    public void removePris(Produkt produkt){
        priser.remove(produkt);
    }

    public double getPris(Produkt produkt){
        return priser.get(produkt);
    }

    public ArrayList<Produkt> getProdukter(){
        ArrayList<Produkt> produkter = new ArrayList<>();
        produkter.addAll(priser.keySet());
        return produkter;
    }

    public String toString(){return this.navn;}
}
