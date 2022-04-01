package application.model;

import java.util.ArrayList;

public class Pris {


    private Produkt produkt;
    private double pris;
    private int klippeKort;

    public Pris(double pris, Produkt produkt){
        this.pris = pris;
        this.produkt = produkt;
        this.klippeKort = 10;
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

    public int getKlippeKort() {
        return klippeKort;
    }

    public String toString(){return produkt + " " + pris;}
}
