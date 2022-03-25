package application.model;

public class Glass extends Oevrige {

    private double størrelse;

    public Glass(String navn, int antal, double barPris, double butiksPris) {
        super(navn, antal, barPris, butiksPris);

        this.størrelse = størrelse;
    }
}
