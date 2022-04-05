package application.model;

public class PrisOgKlip extends Pris {

    private int klipPris;

    public PrisOgKlip(double pris, Produkt produkt, int klipPris) {
        super(pris, produkt);
        this.klipPris = klipPris;
    }

    @Override
    public int getKlip() {
        return klipPris;
    }

    public void setKlip(int klip){
        this.klipPris = klip;
    }

    @Override
    public String toString(){return super.getProdukt() + "   " + super.getPris() + "Kr   " + getKlip() + "Klip";}


}
