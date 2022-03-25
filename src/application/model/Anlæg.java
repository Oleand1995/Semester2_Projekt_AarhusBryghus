package application.model;

public class Anlæg extends UdlejningsProdukter{

    private double leveringsPris;

    public Anlæg(String navn, int antal, double leveringsPris) {
        super(navn, antal);
        this.leveringsPris = leveringsPris;
    }
}
