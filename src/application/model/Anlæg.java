package application.model;

public class Anlæg extends UdlejningsProdukter{

    private double leveringsPris;

    public Anlæg(String navn, int antal, double barPris, double butiksPris, double leveringsPris) {
        super(navn, antal, barPris, butiksPris);
        this.leveringsPris = leveringsPris;
    }
}
