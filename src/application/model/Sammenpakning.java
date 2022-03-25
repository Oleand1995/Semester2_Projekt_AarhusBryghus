package application.model;

public class Sammenpakning extends Oevrige {

    private int antalOel;

    public Sammenpakning(String navn, int antal, double barPris, double butiksPris) {
        super(navn, antal, barPris, butiksPris);

        this.antalOel = antalOel;
    }
}
