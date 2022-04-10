package application.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Prisliste implements Serializable {

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

    public String toString(){return this.situation;}
}
