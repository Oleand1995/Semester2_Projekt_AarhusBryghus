package application.model;

public class Malt extends Oevrige {

    private String vægt;


    public Malt(String navn, int antal, double barPris, double butiksPris) {
        super(navn, antal, barPris, butiksPris);

        this.vægt = vægt;
    }
}
