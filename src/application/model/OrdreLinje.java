package application.model;

import java.util.ArrayList;

public class OrdreLinje {

    private Produkt produkt;
    private int antal;
    private double pris;
    private ArrayList<Produkt> ønskedeProduker;

    OrdreLinje(Produkt produkt, int antal, double pris){
        this.produkt = produkt;
        this.antal = antal;
        this.pris = pris;
        this.ønskedeProduker = new ArrayList<>();

    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public void addØnsketProdukt(Produkt produkt){
        if (!ønskedeProduker.contains(produkt)) {
            ønskedeProduker.add(produkt);
        }
    }

    public void removeØnsketProdukt(Produkt produkt){
        if (ønskedeProduker.contains(produkt)){
            ønskedeProduker.remove(produkt);
        }
    }

    @Override
    public String toString(){
        return this.produkt.getBeskrivelse() + "     Antal:" + antal + "     Stk. Pris:" + this.pris + "Kr";
    }



}
