package application.model;

public class Beklaedning extends Oevrige {

    private String størrelse;
    private String mærke;

    public Beklaedning(String navn, int antal, double barPris, double butiksPris) {
        super(navn, antal, barPris, butiksPris);

        this.størrelse = størrelse;
        this.mærke = mærke;
    }
}
