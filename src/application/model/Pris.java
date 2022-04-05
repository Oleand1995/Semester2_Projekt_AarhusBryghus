package application.model;

import java.util.ArrayList;

public class Pris {


    private Produkt produkt;
    private double pris;

    public Pris(double pris, Produkt produkt){
        this.pris = pris;
        this.produkt = produkt;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public String toString(){return produkt + "   " + pris + "Kr";}

    public int getKlip(){
        return -1;
    }

    public void setKlip(int klip){
    }
}
