package application.model;

public abstract class Produkt {

    private String navn;
    private int antal;
    private double barPris;
    private double butiksPris;

    public Produkt(String navn, int antal, double barPris, double butiksPris){
        this.navn = navn;
        this.antal = antal;
        this.barPris = barPris;
        this.butiksPris = butiksPris;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public double getBarPris() {
        return barPris;
    }

    public void setBarPris(double barPris) {
        this.barPris = barPris;
    }

    public double getButiksPris() {
        return butiksPris;
    }

    public void setButiksPris(double butiksPris) {
        this.butiksPris = butiksPris;
    }
}
