package application.model;

public class Fadøl extends Drikkervare {

    private int klipPris;
    private String størrelse;

    public Fadøl(String navn, int antal, double barPris, double butiksPris, int klipPris, String størrelse) {
        super(navn, antal, barPris, butiksPris);
        this.klipPris = klipPris;
        this.størrelse = størrelse;
    }

    public int getKlipPris() {
        return klipPris;
    }

    public void setKlipPris(int klipPris) {
        this.klipPris = klipPris;
    }

    public String getStørrelse() {
        return størrelse;
    }

    public void setStørrelse(String størrelse) {
        this.størrelse = størrelse;
    }
}
