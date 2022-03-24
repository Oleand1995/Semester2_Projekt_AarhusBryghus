package application.model;

public class Spiritus extends Drikkervare {

    private String størrelse;
    private double alkoProcent;

    public Spiritus(String navn, int antal, double barPris, double butiksPris, String størrelse, double alkoProcent) {
        super(navn, antal, barPris, butiksPris);
        this.størrelse = størrelse;
        this.alkoProcent = alkoProcent;
    }

    public String getStørrelse() {
        return størrelse;
    }

    public void setStørrelse(String størrelse) {
        this.størrelse = størrelse;
    }

    public double getAlkoProcent() {
        return alkoProcent;
    }

    public void setAlkoProcent(double alkoProcent) {
        this.alkoProcent = alkoProcent;
    }
}
