package application.model;

public class Kulsyre extends UdlejningsProdukter{

    private double pant;

    public Kulsyre(String navn, int antal, double barPris, double butiksPris, double pant) {
        super(navn, antal, barPris, butiksPris);
        this.pant = pant;
    }
}
