package application.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Ordrelinje {

    private HashMap<Produkt, Integer> ordrer = new HashMap<>();

    public Ordrelinje(){
    }

    public void addOrdre(Produkt produkt){
        if (!ordrer.containsKey(produkt)){
            ordrer.put(produkt,1);
        }
    }

    public void removeOrdre(Produkt produkt){
        ordrer.remove(produkt);
    }

    public HashMap<Produkt,Integer> getOrdrer(){return new HashMap<>(ordrer);}

}
