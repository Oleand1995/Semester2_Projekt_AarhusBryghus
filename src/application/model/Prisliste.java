package application.model;

import java.util.HashMap;
import java.util.Map;

public class Prisliste {

    private String navn;
    private HashMap<Produkt, Integer> priser = new HashMap<>();

    public Prisliste(String navn){
        this.navn = navn;
    }

    public String getNavn(){return this.navn;}

    public void setNavn(String navn){
        this.navn = navn;
    }

    public void addPris(Produkt produkt, int pris){
        priser.put(produkt, pris);
    }

    public void removePris(Produkt produkt){
        priser.remove(produkt);
    }
}
