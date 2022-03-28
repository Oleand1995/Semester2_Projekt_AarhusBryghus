package application.model;

public class Flaske extends Drikkervare {

    private int klipPris;

    public Flaske(String navn, int antal, int klipPris) {
        super(navn, antal);
        this.klipPris = klipPris;
    }

    public int getKlipPris() {
        return klipPris;
    }

    public void setKlipPris(int klipPris) {
        this.klipPris = klipPris;
    }
}
