package application.model;

public class Fustage extends UdlejningsProdukter{

    private double pant;

    public Fustage(String navn, int antal, double barPris, double butiksPris, double pant) {
        super(navn, antal, barPris, butiksPris);
        this.pant = pant;
    }
}
