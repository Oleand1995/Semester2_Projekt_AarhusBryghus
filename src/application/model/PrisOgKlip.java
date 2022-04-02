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

    @Override
    public String toString(){return super.getProdukt() + "   " + super.getPris() + "Kr   " + getKlip() + "Klip";}


}
