package application.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Prisliste {

    private String situation;
    private ArrayList<Pris> priser = new ArrayList<>();

    public Prisliste(String situation){
        this.situation = situation;
    }

    public Pris createPris(double værdi, Produkt produkt){
        Pris pris = new Pris(værdi, produkt);
        priser.add(pris);
        return pris;
    }

    public Pris createPrisOgKlip(double værdi, Produkt produkt,int klipPris){
        Pris pris = new PrisOgKlip(værdi, produkt,klipPris);
        priser.add(pris);
        return pris;
    }

    public void removePris(Pris pris){
        priser.remove(pris);
    }


    public ArrayList<Pris> getPriser(){return new ArrayList<>(priser);}

    public ArrayList<Pris> getKlipPriser(){
        ArrayList<Pris> prisOgKlips = new ArrayList<>();
        for (Pris p : getPriser()){
            if (p instanceof PrisOgKlip){
                prisOgKlips.add(p);
            }
        }
        return prisOgKlips;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public ArrayList<Produkt> getProdukter(){
        ArrayList produkter = new ArrayList();
        for (Pris pr : priser){
            produkter.add(pr.getProdukt());
        }
        return produkter;
    }


    public String toString(){return this.situation;}
}
