package application.model;

public class Flaske extends Drikkervare {

    private int klipPris;

    public Flaske(String navn, int antal, double barPris, double butiksPris, int klipPris) {
        super(navn, antal, barPris, butiksPris);
        this.klipPris = klipPris;
    }

    public int getKlipPris() {
        return klipPris;
    }

    public void setKlipPris(int klipPris) {
        this.klipPris = klipPris;
    }
}
